package src;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

/**
   A component that displays initial Panel
*/

public class StartPanel extends JPanel{
	public  String display;

	private BufferedImage image;
	private Image backgroundImage;
	private SoundManager soundManager;

	public StartPanel() {
		display ="To start the game press the Start Game button";
		soundManager = SoundManager.getInstance();

		//Insert background image
		backgroundImage = ImageManager.loadImage("Assets/images/Enigma_Start2.png");
		int width = getWidth();
		int height = getHeight();
		if (width == 0 || height == 0){
			width = 850;
			height = 800;
		}
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
//		Font f1 = new Font("Serif",Font.PLAIN,30);
//		g2.setFont(f1);
//		g2.setColor(Color.BLACK);
//		TextLayout textLayout = new TextLayout(display, f1,g2.getFontRenderContext());
//		textLayout.draw(g2,getWidth()/5+60,100);

		//Insert background image
		Graphics2D imageContext = (Graphics2D) image.getGraphics();
		imageContext.drawImage(backgroundImage,0,0,null); //draw the background image

//		Graphics2D imageContext = (Graphics2D) image.getGraphics();
//
//		Font f2 = new Font("Serif",Font.PLAIN,25);
//		g2.setFont(f2);
//		g2.setColor(Color.BLACK);
//		g2.drawString("Enigma is a puzzle game.",465,300);
//		g2.drawString("There will be a riddle displayed at the top  ",400,350);
//		g2.drawString("of a board. The answer to each riddle is a number. To win ",360,400);
//		g2.drawString(" you must answer most riddles correctly without losing all your hearts ",303,450);
//		g2.drawString("There are eight riddles and 3 Hearts. Please enter a name before you click start ",260,500);

	}


}