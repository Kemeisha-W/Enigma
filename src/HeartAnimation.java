package src;

import java.awt.*;


/**
    The FaceAnimation class creates a face animation containing six frames 
    based on three images.
*/
public class HeartAnimation {

	Animation animation;

	private int x;		// x position of animation
	private int y;		// y position of animation

	private int width;
	private int height;

	private int dx;		// increment to move along x-axis
	private int dy;		// increment to move along y-axis

	public HeartAnimation() {

		animation = new Animation();

		x = 5;		// set x position
        	y = 10;		// set y position
        	dx = 0;		// increment to move along x-axis
        	dy = 0;		// increment to move along y-axis

		// load images for blinking face animation

		Image heartAni_1 = ImageManager.loadImage("Assets/images/hearts1.png");
		Image heartAni_2 = ImageManager.loadImage("Assets/images/hearts2.png");
		Image heartAni_3 = ImageManager.loadImage("Assets/images/hearts3.png");
		Image heartAni_4 = ImageManager.loadImage("Assets/images/hearts4.png");
		Image heartAni_5 = ImageManager.loadImage("Assets/images/hearts5.png");


		// create animation object and insert frames

		animation.addFrame(heartAni_1, 250);
		animation.addFrame(heartAni_2, 150);
		animation.addFrame(heartAni_3, 150);
		animation.addFrame(heartAni_4, 150);
		animation.addFrame(heartAni_5, 200);
//		animation.addFrame(heartAni_2, 150);

	}


	public void start() {
		x = 5;
        	y = 10;
		animation.start();
	}

	
	public void update() {
		animation.update();
		x = x + dx;
		y = y + dy;
	}


	public void draw(Graphics2D g2) {
		g2.drawImage(animation.getImage(), x, y, 150, 150, null);
	}

}
