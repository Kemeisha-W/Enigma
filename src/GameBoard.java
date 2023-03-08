package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import static src.GameWindow.soundManager;

public class GameBoard extends JPanel implements Runnable{
    private static BufferedImage image;
    private Image backgroundImage;
    private int a =0;
    public Player player;
    private Scanner scnr;
    private Thread gameThread;
    private HeartAnimation ani;

    //JPanels
    private JPanel riddleP;

    private JLabel riddleL;
    private JLabel option1L;
    private JLabel option2L;
    private JLabel option3L;
    private ArrayList<String> riddles = new ArrayList<String>();
    private ArrayList<String> answers = new ArrayList<String>();
    private String correct = "";
    private int count = 0;
    private boolean isRunning;
    private boolean playAni = false;
    private boolean mClicked = false;

    public GameBoard(GameWindow.CATEGORY c){
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
            scnr = new Scanner(new FileReader(filename));
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }

        riddleP = new JPanel(new GridBagLayout());
        riddleP.setPreferredSize(new Dimension(800,300));
        riddleL = new JLabel();
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
        option3P.add(option3L);


        //Create mouse Listeners
        option1P.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isCorrect(option1L);
            }
        });
        option2P.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isCorrect(option2L);
            }
        });
        option3P.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isCorrect(option3L);
            }
        });


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
        player = new Player();

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

        riddleL.setText(riddles.get(count));
        correct = riddles.get(count);
        option1L.setText(answers.get(randAnswer(count)));
        option2L.setText(answers.get(randAnswer(count)));
        option3L.setText(answers.get(randAnswer(count)));
        System.out.println("Riddle: " + riddles.get(count)+"\n");

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
            gameThread = new Thread (this);
            gameThread.start();
        }
    }


    public void run () {
        isRunning = true;
        while (isRunning){
            while(playAni){
                System.out.println("HearT Lost!");
                if(ani!=null){
                    soundManager.playClip("wrong",false);
                    for(int i=0;i<5;i++){
                        BufferedImage aniImage = new BufferedImage(riddleP.getWidth(),riddleP.getHeight(),BufferedImage.TYPE_INT_RGB);
                        Graphics2D riddleg2 = (Graphics2D) riddleP.getGraphics();
                        ani.draw(riddleg2);
                        try {
                            Thread.sleep(20);
                            ani.update();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                playAni = false;
                //TODO: CHANGE TO HEART PANEL HEART
            }

            if(count == 8){
                soundManager.playClip("levelUp",false);
                //TODO: Add animation and sound clip and Label displaying information
            }
            if(mClicked){
                count++;
                System.out.println("Riddle: " + riddles.get(count)+"\n");
                riddleL.setText(riddles.get(count));
                correct = riddles.get(count);
                option1L.setText(answers.get(randAnswer(count)));
                option2L.setText(answers.get(randAnswer(count)));
                option3L.setText(answers.get(randAnswer(count)));

            }
        }

    }

    public void isCorrect(JLabel clicked) {

        mClicked = true;
        if(clicked.getText().equals(correct)){
            diskAni();
            System.out.println("correct yes");
            return;
        }
        playAni = true;
        ani = new HeartAnimation();
        ani.start();
        count--;
        player.hearts--;
        System.out.println("wrong yes");
    }

    public void diskAni(){

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
    }

    public void endGame() {					// end the game thread
        isRunning = false;
        soundManager.stopClip ("background");
    }

}
