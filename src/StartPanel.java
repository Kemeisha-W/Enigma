package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
   A component that displays initial Panel
*/

public class StartPanel extends JPanel {
	private BufferedImage image;
	private Image backgroundImage;
	public ButtonCustom startB;


	public StartPanel(int width) {
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbCon = new GridBagConstraints();
		gbCon.fill = GridBagConstraints.HORIZONTAL;
		gbCon.insets = new Insets(5,3,5,3);

		Font buttonF;
		//Create Custom Font
		try{
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Font f1 = Font.createFont(Font.TRUETYPE_FONT,new File("Assets/fonts/button_shield/Button Shield Personal Use Only.otf"));
			f1.deriveFont(13f);
			ge.registerFont(f1);
			buttonF = new Font("Button Shield Personal Use Only",Font.PLAIN,30);
		}catch(IOException|FontFormatException e){
			//Handle exception
			System.out.println("Message: " + e.getMessage());
			buttonF = new Font("Arial", Font.PLAIN, 20 );
		}

		//Insert background image
		backgroundImage = ImageManager.loadBufferedImage("Assets/images/Enigma_Start2 (Custom).png");
		image = new BufferedImage(width, 907, BufferedImage.TYPE_INT_RGB);

		//Insert Custom Buttons
		startB = new ButtonCustom();
		startB.setPreferredSize(new Dimension(200, 50));
		startB.setHorizontalAlignment(SwingConstants.CENTER);
		startB.setStyle(ButtonCustom.ButtonStyle.SECONDARY);
		startB.setText("Start");
		startB.setRound(30);
		startB.setFont(buttonF);

		gbCon.gridx = 0;
		gbCon.gridy = 0;
		add(startB);
		setLayout(gb);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Graphics2D imageContext = (Graphics2D) image.getGraphics();
		imageContext.drawImage(backgroundImage,0,0,null); //draw the background image
		g2.drawImage(image, 0, 50, image.getWidth(), image.getHeight(), null);
		imageContext.dispose();
	}


}