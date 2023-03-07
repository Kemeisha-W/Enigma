package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

public class GameBoard extends JPanel implements Runnable{
    private int colNum;
    private Canvas canvas;
    public static int SPACING = 10;
    public int counter =0;
    private BufferedImage image;
    private Image backgroundImage;
    public static final int COLS=8;
    private int a =0;
    public BoardSpace value[];
    public Player player;
    private GameWindow.CATEGORY category;
//    private Arr

    public GameBoard(Player p){
        value = new BoardSpace[COLS];
        setLayout(new GridBagLayout());
        GridBagConstraints gbCon = new GridBagConstraints();
        gbCon.fill = GridBagConstraints.BOTH;

        gbCon.insets = new Insets(5,3,5,3);
//        switch (category){
//
//        }
        gbCon.gridy = 1;
        JLabel question = new JLabel();
        question.setSize(getWidth()/3,getHeight()-100);
        question.setText("<html> Riddles will be displayed Here</html>");
        add(question);

        gbCon.gridy = 3;
        JLabel option1 = new JLabel();
        option1.setText("<html> Option1</html>");
        add(option1);

        gbCon.gridy = 4;
        JLabel option2 = new JLabel();
        option2.setText("<html> Option2</html>");
        add(option2);

        gbCon.gridy = 5;
        JLabel option3 = new JLabel();
        option3.setText("<html> Option3</html>");
        add(option3);


        //Create and initialize Players
        player = p;
        int numDisks = player.numDisks;



        backgroundImage = ImageManager.loadBufferedImage("Assets/images/Enigma.png");
        image = new BufferedImage(802, 907, BufferedImage.TYPE_INT_RGB);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D imageContext = (Graphics2D) image.getGraphics();
        imageContext.drawImage(backgroundImage,0,0,null); //draw the background image
        g2.drawImage(image, 0, 50, image.getWidth(), image.getHeight(), null);
        imageContext.dispose();
    }
//    public void setCanvas(Canvas canvas) {this.canvas = canvas;}
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

    public void run () {
        player.chosenCol = colNum;
        player.disks[a].setX(value[colNum-1].x);
        if(counter != 9) {
            //Drop Disk to reveal answer
            try {
                canvas = player.disks[a].dropDisk(canvas, value[colNum - 1], isCorrect());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //If it is not correct then
            if (!value[colNum - 1].isCorrect) {
                value[colNum - 1].hasDisk = false;
                player.numDisks--;
                player.hearts--;
                if (player.hearts >= 0) {
                    counter--;
                    //Draw Heart to show the lost heart
//                    try {//TODO: CHANGE TO HEART PANEL HEART
////                        drawHeart();
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
                    canvas.repaint();
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else if (player.hearts<=0) {
                    setScore();
                    return;
                }
            }else{
                value[colNum-1].isCorrect = true;
                player.correctCounter++;
            }
        }else if(counter >= 9){
            setScore();
            return;
        }
        counter++;
        if (counter < 8){
//            rule.setText(player.questions.get(counter));
            revalidate();
            player.numDisks--;
            a++;
        }
        else {
            setScore();
            return;
        }
//        text.setText("Enter new Number");
        revalidate();
    }

    public Boolean isCorrect() {
        String question = player.questions.get(counter);
        if (player.answer.get(question) == colNum){
            return true;
        }
        return false;
    }

    private void setScore(){
//        text.setText("<html>Game Over</html>");
//        rule.setText("<html>Score: " +player.correctCounter +" out of 8.<br/>To Play Again click StartGame</html>");
        revalidate();
    }



//    public void startNewGame() {				// initialise and start a new game thread
//
//        isPaused = false;
//
//        if (gameThread == null || !isRunning) {
//            //soundManager.playClip ("background", true);
//            createGameEntities();
//            gameThread = new Thread (this);
//            gameThread.start();
//
//            if (animation != null) {
//                animation.start();
//            }
//        }
//    }
//TODO::ABOVE AND BELOW
    public void endGame() {					// end the game thread
        boolean isRunning = false;
        //soundManager.stopClip ("background");
    }
}
