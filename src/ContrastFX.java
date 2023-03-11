package src;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ContrastFX implements ImageFX {

	private static final int WIDTH = 1000;		// width of the image
	private static final int HEIGHT = 719;		// height of the image

	private JPanel panel;

	private int x;
	private int y;

	private BufferedImage i;		// image for sprite effect
	private BufferedImage copy;			// copy of image

	double contrast, contrastChange;		// to alter the contrast of the image

	public ContrastFX(JPanel p) {
		panel = p;

		Random random = new Random();
		x = 0;
		y = 0;

		contrast = 2.0;				// range is 0 to 3.0
		contrastChange = 0.01;			// increase of contrast on each update

		i = ImageManager.loadBufferedImage("Assets/images/Enigma_Back.png");

	}


	private int truncate (int colourValue) {	// keeps colourValue within [0..255] range
		if (colourValue > 255)
			return 255;

		if (colourValue < 0)
			return 0;

		return colourValue;
	}


	private int applyContrast (int pixel) {

    		int alpha, red, green, blue;
		int newPixel;
		
		alpha = (pixel >> 24) & 255;
		red = (pixel >> 16) & 255;
		green = (pixel >> 8) & 255;
		blue = pixel & 255;

		// Increase or decrease the value of the RGB components based on the value of contrast

		red = (int) (contrast * red);
		green = (int) (contrast * green);
		blue = (int) (contrast * blue); 

		// Check the boundaries for 8-bit RGB components [0..255]

		red = truncate (red);
		green = truncate (green);
		blue = truncate (blue);
		
		newPixel = blue | (green << 8) | (red << 16) | (alpha << 24);

		return newPixel;
	}


	public void draw (Graphics2D g2) {

		copy = ImageManager.copyImage(i);
							// make copy of image for brightness effect

		int imWidth = copy.getWidth();
		int imHeight = copy.getHeight();

    		int [] pixels = new int[imWidth * imHeight];
    		copy.getRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);

    		int alpha, red, green, blue;

		for (int i=0; i<pixels.length; i++) {
			pixels[i] = applyContrast(pixels[i]);
		}

    		copy.setRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);	

		g2.drawImage(copy, x, y, WIDTH, HEIGHT, null);

	}

	public BufferedImage getCopy(){
		copy = ImageManager.copyImage(i);
		// make copy of image for brightness effect

		int imWidth = copy.getWidth();
		int imHeight = copy.getHeight();

		int [] pixels = new int[imWidth * imHeight];
		copy.getRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);

		int alpha, red, green, blue;

		for (int i=0; i<pixels.length; i++) {
			pixels[i] = applyContrast(pixels[i]);
		}

		copy.setRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);
		return  copy;
	}

	public Rectangle2D.Double getBoundingRectangle() {
		return new Rectangle2D.Double (x, y, WIDTH, HEIGHT);
	}


	public void update() {				// modify contrast (0 to 3.0)
	
		contrast = contrast + contrastChange;

		if (contrast > 3.0) {
			contrast = 3.0;
			contrastChange = -1 * contrastChange;
		}
		else
		if (contrast < 0) {
			contrast = 0;
			contrastChange = -1 * contrastChange;
		}	
	}
}