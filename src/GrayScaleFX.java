package src;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GrayScaleFX implements ImageFX {

	private static final int WIDTH = 50;		// width of the image
	private static final int HEIGHT = 50;		// height of the image

	private JPanel panel;

	private int x;
	private int y;

	private BufferedImage image;		// image for heart effect
	private BufferedImage copyImage;		// copy of image


	int time, timeChange;				// to control when the image is grayed
	boolean originalImage, grayImage;


	public GrayScaleFX(JPanel p) {
		this.panel = p;

		x = 150;
		y = 0;

		time = 0;				// range is 0 to 40
		timeChange = 1;				// set to 1
		originalImage = true;
		grayImage = false;

		image = ImageManager.loadBufferedImage("Assets/images/grayTrophy.png");
		copyImage = ImageManager.copyImage(image);
							//  make a copy of the original image
		copyToGray();

	}


	private int toGray (int pixel) {

  		int alpha, red, green, blue, gray;
		int newPixel;

		alpha = (pixel >> 24) & 255;
		red = (pixel >> 16) & 255;
		green = (pixel >> 8) & 255;
		blue = pixel & 255;

		// Calculate the value for gray

		gray = (red + green + blue) / 3;

		// Set red, green, and blue channels to gray

		red = green = blue = gray;

		newPixel = blue | (green << 8) | (red << 16) | (alpha << 24);

		return newPixel;
	}


	public BufferedImage copyToGray() {
		int imWidth = copyImage.getWidth();
		int imHeight = copyImage.getHeight();

    		int [] pixels = new int[imWidth * imHeight];
    		copyImage.getRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);

		for (int i=0; i<pixels.length; i++) {
			pixels[i] = toGray(pixels[i]);
		}
  
    	 	copyImage.setRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);
		return copyImage;
	}	


	public void draw (Graphics2D g2) {
			g2.drawImage(copyImage, x, 0, WIDTH, HEIGHT, null);
	}


	public void update() {				// modify time and change the effect if necessary
	
		time = time + timeChange;

		if (time < 20) {			// original image shown for 20 units of time
			originalImage = true;
			grayImage = false;
		}
		else
		if (time < 40) {			// gray scale image shown for 20 units of time
			originalImage = false;
			grayImage = true;
		}
		else {		
			time = 0;
		}
	}

}