package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class GameOverPanel extends JPanel  implements Runnable{
    private BufferedImage image;
    private GameOverAnimation animation;
    private Thread gameOver;
    private Window win;

    public GameOverPanel() {
        image = new BufferedImage(1000, 719, BufferedImage.TYPE_INT_RGB);
        animation = new GameOverAnimation();
    }


    public void setAnimation(Window win) {				// initialise and start the game thread
        this.win = win;
        if (gameOver == null) {
            gameOver = new Thread (this);
            gameOver.start();

            if (animation != null) {
                animation.start();
            }

        }
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i <100;i++){
                gameRender();
                Thread.sleep(10);
                animation.update();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        win.dispose();
    }

    public void gameRender() {
        Graphics2D imageContext = (Graphics2D) image.getGraphics();
        Graphics2D g2 = (Graphics2D) getGraphics();	// get the graphics context for the panel
        animation.draw(imageContext);
        g2.drawImage(image,0,0,null);
        g2.dispose();
        imageContext.dispose();
    }

}
