package src;

import javax.swing.*;
import java.awt.*;

public class Title extends JPanel {

	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Font f1 = new Font("MONOSPACED",Font.BOLD,70);
		g2.setFont(f1);
		g2.setColor(Color.BLACK);
		g2.drawString("Enigma",470,55);
	}

}