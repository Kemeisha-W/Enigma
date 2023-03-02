package src;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class GameBoard extends JPanel implements Runnable{
    private int colNum;
    private Canvas canvas;
    public static int SPACING = 10;
    public int counter =0;
    private JLabel text;
    private JLabel rule;
    public static final int COLS=8;
    private int a =0;
    public BoardSpace value[];
    public Player player;


    public GameBoard(Player p,JLabel text,JLabel rule){
        value = new BoardSpace[COLS];

        //Create and initialize Players
        player = p;
        int numDisks = player.numDisks;


        this.text = text;
        this.rule = rule;


        // Set the colour of each player's disk
        for (int i =0;i<numDisks;i++){
            player.disks[i].cFill = new Color(255,194,71);
            player.disks[i].cStroke= new Color(255,182,71);
        }
    }

    public void setCanvas(Canvas canvas) {this.canvas = canvas;}
    public void updateBoard(int colNumber){
        this.colNum =colNumber;
        if(colNumber<9&&colNumber>-1){
            if (!isCorrect()) {
                value[colNum - 1].isCorrect = false;
            } else {
                value[colNum - 1].isCorrect = true;
            }
            run();
        }else{
            rule.setText("Enter a number between 1 and 8");
            revalidate();;
        }
    }

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
                    try {
                        drawHeart();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
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
            rule.setText(player.questions.get(counter));
            revalidate();
            player.numDisks--;
            a++;
        }
        else {
            setScore();
            return;
        }
        text.setText("Enter new Number");
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
        text.setText("<html>Game Over</html>");
        rule.setText("<html>Score: " +player.correctCounter +" out of 8.<br/>To Play Again click StartGame</html>");
        revalidate();
    }

    public void drawHeart() throws InterruptedException {
        Graphics g= canvas.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        int x = canvas.getWidth()/2;
        int width = 100;
        int height =100;
        int y = canvas.getHeight()/2;
        int[] triangleX = {
                x - 2*width/18,
                x + width + 2*width/18,
                (x - 2*width/18 + x + width + 2*width/18)/2};
        int[] triangleY = {
                y + height - 2*height/3,
                y + height - 2*height/3,
                y + height };
        g2.setColor(new Color(242,7,7));
        g2.fillOval(485, y, width/2 + width/6+15, 45);
        g2.fillOval(540, y, width/2 + width/6+10, 45);
        g2.fillPolygon(triangleX, triangleY, triangleX.length);
        Font f1 = new Font("MONOSPACED",Font.BOLD,50);
        g2.setFont(f1);
        g2.setColor(Color.BLACK);
        g2.drawString("You have "+player.hearts+" lives Left",260,300);
        sleep(500);
        g2.dispose();
    }

//    public void startNewGame() {				// initialise and start a new game thread
//
//        if (gameThread == null || !isRunning) {
//            //soundManager.playClip ("background", true);
//            gameThread = new Thread (this);
//            gameThread.start();
//
//            if (animation != null) {
//                animation.start();
//            }
//        }
//    }
}
