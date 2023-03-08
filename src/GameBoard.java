package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.sleep;
import static src.GameWindow.soundManager;

public class GameBoard extends JPanel implements Runnable{
    private int colNum;
    public static int SPACING = 10;
    public int counter =0;
    private BufferedImage image;
    private Image backgroundImage;
    public static final int COLS=8;
    private int a =0;
    public BoardSpace value[];
    public Player player;
    private Scanner scnr;
    private Thread gameThread;

    //JPanels
    private JLabel riddleL;
    private JLabel option1L;
    private JLabel option2L;
    private JLabel option3L;
    private ArrayList<String> riddles = new ArrayList<String>();
    private ArrayList<String> answers = new ArrayList<String>();
    private String correct = "";
    private int count = 0;
    private boolean isRunning;

    public GameBoard(Player p, GameWindow.CATEGORY c){
        value = new BoardSpace[COLS];
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbCon = new GridBagConstraints();

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
            //TODO: use data to build the question and answers
            scnr = new Scanner(new FileReader(filename));
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }

        JPanel riddleP = new JPanel(new GridBagLayout());
        riddleP.setPreferredSize(new Dimension(800,300));
        riddleL = new JLabel();
        riddleL.setSize(getWidth()/3,getHeight()-100);
        riddleP.add(riddleL);


        JPanel option1P = new JPanel(new GridBagLayout());
        option1P.setPreferredSize(new Dimension(600,50));
        option1L = new JLabel();
        option1P.add(option1L);


        JPanel option2P = new JPanel(new GridBagLayout());
        option2P.setPreferredSize(new Dimension(600,50));
        option2L = new JLabel();
        option2P.add(option2L);


        JPanel option3P = new JPanel(new GridBagLayout());
        option3P.setPreferredSize(new Dimension(600,50));
        option3L = new JLabel();
        option3P.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        option3P.add(option3L);

        //Set Riddle Arraylist and Answer Arraylist
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            if(line.contains("Riddle: ")){
                line = line.replace(" Riddle: ","");
                riddles.add(line);
            } else if (line.contains("Answer: ")) {
                line = line.replace("Answer: ","");
                answers.add(line);
            }
        }

//        //Create and initialize Players
//        player = p;
//        int numDisks = player.numDisks;
        setBackground(GameWindow.background);
        backgroundImage = ImageManager.loadBufferedImage("Assets/images/Enigma (Custom).png");
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
        gbCon.gridy = 5;
        add(option3P,gbCon);
    }

    public void gameRender(){
        Graphics2D imageContext = (Graphics2D) image.getGraphics();

        imageContext.drawImage(backgroundImage,0,0,null); //draw the background image

        Graphics2D g2 = (Graphics2D) getGraphics();
        g2.drawImage(image, 0, 0, null);
    }

    public void startGame() {				// initialise and start the game thread
        if (gameThread == null) {
            gameThread = new Thread (this);
            gameThread.start();
        }
    }

    public void run () {
        isRunning = true;
//        while (isRunning){
        gameRender();
            System.out.println("Riddles: " + riddles+"\n");
            System.out.println("Answers: " + answers+"\n");
            if(count == 8){
                //TODO: Add animation and sound clip and Label displaying information
            }
            riddleL.setText(riddles.get(count));
            correct = riddles.get(count);
            option1L.setText(answers.get(randAnswer(count)));
            option2L.setText(answers.get(randAnswer(count)));
            option3L.setText(answers.get(randAnswer(count)));
//        }
//        player.chosenCol = colNum;
//        player.disks[a].setX(value[colNum-1].x);
            //Drop Disk to reveal answer
//            try {
//                canvas = player.disks[a].dropDisk(canvas, value[colNum - 1], isCorrect());
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            //If it is not correct then
//            if (!value[colNum - 1].isCorrect) {
//                value[colNum - 1].hasDisk = false;
//                player.numDisks--;
//                player.hearts--;
//                if (player.hearts >= 0) {
//                    counter--;
//                    //Draw Heart to show the lost heart
////                    try {//TODO: CHANGE TO HEART PANEL HEART
//////                        drawHeart();
////                    } catch (InterruptedException e) {
////                        throw new RuntimeException(e);
////                    }
//                    canvas.repaint();
//                    try {
//                        sleep(500);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }else if (player.hearts<=0) {
//                    setScore();
//                    return;
//                }
//            }else{
//                value[colNum-1].isCorrect = true;
//                player.correctCounter++;
//            }
//        if (counter < 8){
////            rule.setText(player.questions.get(counter));
//            revalidate();
//            player.numDisks--;
//            a++;
//        }
//            setScore();
//            return;
//        text.setText("Enter new Number");
//        revalidate();
    }

//    public void updateBoard(int colNumber){
//        this.colNum =colNumber;
//        if(colNumber<9&&colNumber>-1){
//            if (!isCorrect()) {
//                value[colNum - 1].isCorrect = false;
//            } else {
//                value[colNum - 1].isCorrect = true;
//            }
//            run();
//        }else{
//            rule.setText("Enter a number between 1 and 8");
//            revalidate();;
//        }
//    }

    private void gameOver(){
        soundManager.playClip("gameOver",false);
        GameOverAnimation animation = new GameOverAnimation();
        Graphics2D imageContext = (Graphics2D) image.getGraphics();
        animation.start();
        animation.draw(imageContext);
        Graphics2D g2 = (Graphics2D) getGraphics();
//        g2.drawImage(,0,0,null);
//        imageContext.dispose();
    }

    public void isCorrect(JLabel clicked) {
        if(clicked.getText().equals(correct)){
            //TODO: CORRECT OR WRONG
        }else{

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

    //TODO: Set score
    private void setScore(){
//        text.setText("<html>Game Over</html>");
//        rule.setText("<html>Score: " +player.correctCounter +" out of 8.<br/>To Play Again click StartGame</html>");
        revalidate();
    }

    public void restartGame() {				// initialise and start a new game thread
        if (gameThread == null || !isRunning) {
            gameThread = new Thread (this);
            gameThread.start();
        }
    }

    public void endGame() {					// end the game thread
        isRunning = false;
        soundManager.stopClip ("background");
        gameOver();
    }

}
