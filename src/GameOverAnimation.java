package src;

import java.awt.*;


/**
    The FaceAnimation class creates a face animation containing six frames 
    based on three images.
*/
public class GameOverAnimation {

	Animation animation;

	private int x;		// x position of animation
	private int y;		// y position of animation

	private int width;
	private int height;

	private int dx;		// increment to move along x-axis
	private int dy;		// increment to move along y-axis

	public GameOverAnimation() {

		animation = new Animation();

		x = 0;		// set x position
        	y = 0;		// set y position
        	dx = 0;		// increment to move along x-axis
        	dy = 0;		// increment to move along y-axis

		// load images for blinking face animation
		Image gameOV_2 = ImageManager.loadImage("Assets/images/go2.jpg");
		Image gameOV_3 = ImageManager.loadImage("Assets/images/go4.jpg");


		// create animation object and insert frames
		animation.addFrame(gameOV_2, 150);
		animation.addFrame(gameOV_3, 150);
		animation.addFrame(gameOV_2, 150);
		animation.addFrame(gameOV_3, 150);
		animation.addFrame(gameOV_2, 150);

	}


	public void start() {
		x = 0;
		y = 0;
		animation.start();
	}

	
	public void update() {
		animation.update();
		x = x + dx;
		y = y + dy;
	}


	public void draw(Graphics2D g2) {
		g2.drawImage(animation.getImage(), x, y, 1000, 719, null);
	}

}
