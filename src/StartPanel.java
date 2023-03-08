package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
   A component that displays initial Panel
*/

public class StartPanel extends JPanel {
	private BufferedImage image;
	private Image backgroundImage;
	public ButtonCustom startB;
	public ButtonCustom infoB;


	public StartPanel(int width) {
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbCon = new GridBagConstraints();
		gbCon.fill = GridBagConstraints.HORIZONTAL;
		gbCon.insets = new Insets(5,3,5,3);

		//Insert background image
		backgroundImage = ImageManager.loadBufferedImage("Assets/images/Enigma_Start2 (Custom).png");
		image = new BufferedImage(width, 907, BufferedImage.TYPE_INT_RGB);

		//Insert Custom Buttons
		startB = new ButtonCustom();
		startB.setPreferredSize(new Dimension(200, 50));
		startB.setFont(new Font("Arial", Font.PLAIN, 20 ));
//		startB.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 2));
		startB.setHorizontalAlignment(SwingConstants.CENTER);
		startB.setStyle(ButtonCustom.ButtonStyle.SECONDARY);
		startB.setText("Start Game");
		startB.setRound(30);

		infoB = new ButtonCustom();
		infoB.setPreferredSize(new Dimension(200, 50));
		infoB.setFont(new Font("Arial", Font.PLAIN, 20 ));
//		infoB.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 2));
		infoB.setHorizontalAlignment(SwingConstants.CENTER);
		infoB.setText("Game Information");
		infoB.setRound(30);
		infoB.setStyle(ButtonCustom.ButtonStyle.SECONDARY);
		setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

		gbCon.gridx = 0;
		gbCon.gridy = 0;
		add(startB);

		gbCon.gridx = 0;
		gbCon.gridy = 1;
		add(infoB);
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