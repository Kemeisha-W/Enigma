package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;
import static src.GameWindow.soundManager;

public class GameBoard extends JPanel implements Runnable{
    private static BufferedImage image;
    private Image backgroundImage;
    public Player player;
    private Scanner scnr;
    private Thread gameThread;
    private HeartAnimation ani;
    private Color labelColor = new Color(255, 195, 30);

    //JPanels
    private JPanel riddleP;
    private JPanel heartPanel;
    private JPanel option1P;
    private JPanel option2P;
    private JPanel option3P;


    //JLabel
    private JLabel riddleL;
    private JLabel option1L;
    private JLabel option2L;
    private JLabel option3L;
    private Font labelF;

    //Array Lists
    private ArrayList<String> riddles = new ArrayList<String>();
    private ArrayList<String> answers = new ArrayList<String>();

    private String correct = "";
    private int count = 0;
    private int num =0;
    private boolean isRunning = true;
    private boolean playAni = false;
    private boolean isCorrect;
    private boolean once = true;
    private boolean gameOver = false;
    private Disk d1;
    private Disk d2;
    private GridBagConstraints gbCon = new GridBagConstraints();

    public GameBoard(GameWindow.CATEGORY c, JPanel hp){
        GridBagLayout gb = new GridBagLayout();
        this.heartPanel = hp;
        player = new Player();
        Font riddleF;
        //Create Custom Font
        try{
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font f1 = Font.createFont(Font.TRUETYPE_FONT,new File("Assets/fonts/eastside_valley/Eastside Valley Personal Use.otf"));
            ge.registerFont(f1);
            riddleF = new Font("Eastside Valley Personal Use",Font.PLAIN,50);
        }catch(IOException | FontFormatException e){
            //Handle exception
            System.out.println("Message: " + e.getMessage());
            riddleF = new Font("Arial", Font.PLAIN, 30 );
        }
        gbCon.insets = new Insets(5,3,5,3);
        String filename = "";
        switch (c){
            case ENGLISH -> filename = "Assets/files/English Riddles.txt";
            case FUNNY -> filename = "Assets/files/Funny Riddles.txt";
            case WORD -> filename = "Assets/files/Word Riddles.txt";
            case MATH -> filename = "Assets/files/Math Riddles.txt";
            case HARD -> filename = "Assets/files/Hard Riddles.txt";
        }
        try{
            scnr = new Scanner(new FileReader(filename));
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }

        //Create Custom Font
        try{
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font f1 = Font.createFont(Font.TRUETYPE_FONT,new File("Assets/fonts/kg_defying_gravity/KGDefyingGravity.ttf"));
            ge.registerFont(f1);
            labelF = new Font("KGDefyingGravity",Font.PLAIN,25);
        }catch(IOException | FontFormatException e){
            //Handle exception
            System.out.println("Message: " + e.getMessage());
            labelF = new Font("Arial", Font.PLAIN, 25 );
        }

        gbCon.gridx=0;
        gbCon.gridy=0;

        riddleP = new JPanel(new GridBagLayout());
        riddleP.setPreferredSize(new Dimension(800,300));
        riddleL = new JLabel();
        riddleL.setFont(riddleF);
        riddleL.setPreferredSize(new Dimension(800,300));
        riddleL.setHorizontalAlignment(SwingConstants.CENTER);
        riddleP.add(riddleL);

        option1P = new JPanel(new GridBagLayout());
        option1P.setPreferredSize(new Dimension(600,50));
        option1L = new JLabel();
        option1L.setFont(labelF);
        option1P.setBackground(labelColor);
        option1P.add(option1L);


        option2P = new JPanel(new GridBagLayout());
        option2P.setPreferredSize(new Dimension(600,50));
        option2L = new JLabel();
        option2P.setBackground(labelColor);
        option2L.setFont(labelF);
        option2P.add(option2L);


        option3P = new JPanel(new GridBagLayout());
        option3P.setPreferredSize(new Dimension(600,50));
        option3L = new JLabel();
        option3L.setFont(labelF);
        option3P.setBackground(labelColor);
        option3P.add(option3L);


        //Create mouse Listeners
        option1P.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    isClicked(option1L,option1P);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        option2P.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    isClicked(option2L,option2P);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        option3P.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    isClicked(option3L,option3P);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        //Set Riddle Arraylist and Answer Arraylist
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            if(line.contains("Riddle: ")){
                line = line.replace(" Riddle: ","");
                line = line.substring(2);
                riddles.add(line);
            } else if (line.contains("Answer: ")) {
                line = line.replace("Answer: ","");
                answers.add(line);
            }
        }

        setBackground(GameWindow.background);
        backgroundImage = ImageManager.loadBufferedImage("Assets/images/Enigma_Back.png");
        image = new BufferedImage(1000, 719, BufferedImage.TYPE_INT_RGB);
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setLayout(gb);

        //Add to Game Board
        gbCon.gridx = 0;
        gbCon.gridy = 1;
        add(riddleP,gbCon);
        gbCon.gridy = 2;
        add(option1P,gbCon);
        gbCon.gridy = 3;
        add(option2P,gbCon);
        gbCon.gridy = 4;
        add(option3P,gbCon);

        riddleL.setText("<html><div style='text-align: center;'>LEVEL 1<br> There are two levels and five riddles a level." +
                "<br>If you choose the wrong answer you will loose a heart. You only have three hearts.  </html>");
        correct = "Click here to start";
        option1L.setText(correct);
        count = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D imageContext = (Graphics2D) image.getGraphics();
        imageContext.drawImage(backgroundImage,0,0,null); //draw the background image
        g2.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        imageContext.dispose();
    }

    public void startGame() {				// initialise and start the game thread
        if (gameThread == null) {
            isCorrect = false;
            gameThread = new Thread (this);
            gameThread.start();
        }
    }


    public void run () {
        try {
            while (isRunning){
                while(playAni){
                    if(ani!=null){

                        for(int i=0;i<19;i++){
                            Graphics2D riddleG2 = (Graphics2D) riddleP.getGraphics();
                            ani.draw(riddleG2);
                            sleep(20);
                            riddleP.repaint();
                            ani.update();
                        }
                    }
                    playAni = false;
                    riddleP.repaint();
                }

                if(count == 5){
                    if(once){
                        soundManager.stopClip("background");
                        soundManager.playClip("levelUp",false);
                        Graphics2D rg2 = (Graphics2D) riddleP.getGraphics();
                        BufferedImage levelImage = ImageManager.loadBufferedImage("Assets/images/levelup (Custom).jpg");
                        rg2.drawImage(levelImage, 90, 0, 600, 300, null);
                        once = false;
                        sleep(600);
                        repaint();
                        count++;
                        soundManager.resumeClip("background",true);
                    }
                } else if (count == 10) {
                    soundManager.stopClip("background");
                    soundManager.playClip("gameFinished",false);
                    ContrastFX contr = new ContrastFX(this);
                    backgroundImage = contr.getCopy();
                    riddleL.setText("<html><div style='text-align: center;'>Thanks for playing!" +
                            " <br>To try another category click Restart Game</html>");
                    riddleL.setBackground(Color.BLACK);
                    riddleL.setFont(labelF);
                    remove(option1P);
                    remove(option2P);
                    remove(option3P);
                    repaint();
                    break;
                }
                if(isCorrect && !gameOver){
                    count++;
                    riddleL.setText("<html><div style='text-align: center;'>"+riddles.get(count)+"</html>");
                    correct = answers.get(count);
                    option1L.setText(answers.get(count));
                    option2L.setText(answers.get(randAnswer(count)));
                    option3L.setText(answers.get(randAnswer(count)));
                    revalidate();
                    isCorrect = false;
                    riddleP.repaint();
                } else if (gameOver) {
                    remove(option1P);
                    remove(option2P);
                    remove(option3P);
                }
                sleep(50);
            }
        } catch (InterruptedException e) {
        }
    }

    public void isClicked(JLabel clicked, JPanel clickedP) throws InterruptedException {
        System.out.println("Correct: "+clicked.getText());
        if(clicked.getText().equals("Click here to start")){
            isCorrect = true;
        }else if(!gameOver){
            d1 = new Disk("Assets/images/icons8-puzzled-64.png");
            d2 = new Disk("Assets/images/icons8-puzzle-64.png");
            d2.setX(750);
            moveDisk(riddleP);
            if(clicked.getText().equals(correct)){
                soundManager.stopClip("background");
                soundManager.playClip("correct",false);
                Graphics2D g2 = (Graphics2D) riddleP.getGraphics();
                g2.setColor(Color.WHITE);
                g2.fill (new Rectangle2D.Double (0, 0, 800, 300));
                SepiaFX sepia = new SepiaFX(clickedP);
                sepia.draw(g2);
                sleep(400);
                isCorrect = true;
                clickedP.repaint();
                soundManager.resumeClip("background",true);
                return;
            }
            soundManager.stopClip("background");
            soundManager.playClip("wrong",false);
            Graphics2D g2 = (Graphics2D) clickedP.getGraphics();
//            g2.setColor(labelColor);
            g2.fill (new Rectangle2D.Double (0,0 , 600, 50));
            GrayScaleFX gray = new GrayScaleFX(clickedP);
            gray.draw(g2);
            if(num >-1 && num<3){
                heartPanel.remove(0);
                heartPanel.repaint();
                num++;
                count--;
                if (num == 3) {
                    riddleL.setText("<html>Game Over <br> Restart Game </html>");
                    gameOver = true;
                    isRunning = false;
                    gameThread = null;
                }
            }
            sleep(400);
            playAni = true;
            ani = new HeartAnimation();
            ani.start();
            player.hearts--;
            sleep(200);
            clickedP.repaint();
            soundManager.resumeClip("background",true);
        }

    }

    private void moveDisk(JPanel panel) throws InterruptedException{
        Graphics2D g2 = (Graphics2D) panel.getGraphics();
        d1.draw(g2);
        d2.draw(g2);
        d2.left = true;
        while(!d1.collisionCheck(d2)){
            d1.moveDisk(2);
            d2.moveDisk(1);
            g2.setColor (Color.WHITE);
            g2.fill (new Rectangle2D.Double (0, 0, 800, 300));
            d1.draw(g2);
            d2.draw(g2);
            g2.drawImage(d1.disk,d1.getX(),0,WIDTH, HEIGHT, null);
            g2.drawImage(d2.disk,d2.getX(),0,WIDTH, HEIGHT, null);
            sleep(20);
        }
    }

    private int randAnswer(int ansNum){
        Random rand = new Random();
        int num = rand.nextInt(riddles.size());
        while(true){
            if(num != ansNum){
                return num;
            }else{
                num = rand.nextInt(riddles.size());
            }
        }
    }

    public void restartGame() {				// initialise and start a new game thread
        if (gameThread == null || !isRunning) {
            gameThread = new Thread (this);
            gameThread.start();
        }
        count =0;
        soundManager.playClip ("background",true);
        isRunning = true;

    }

    public void endGame() {					// end the game thread
        isRunning = false;
        soundManager.stopClip ("background");
    }
}
