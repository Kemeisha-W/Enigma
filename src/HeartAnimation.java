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


	public HeartAnimation() {

		animation = new Animation();

		x = 0;		// set x position
		y =0;		// set y position

		// load images for blinking face animation

		Image heartAni_1 = ImageManager.loadImage("Assets/images/hearts1.png");
		Image heartAni_2 = ImageManager.loadImage("Assets/images/hearts2.png");
		Image heartAni_3 = ImageManager.loadImage("Assets/images/hearts3.png");
		Image heartAni_4 = ImageManager.loadImage("Assets/images/hearts4.png");
		Image heartAni_5 = ImageManager.loadImage("Assets/images/hearts5.png");


		// create animation object and insert frames

		animation.addFrame(heartAni_1, 100);
		animation.addFrame(heartAni_2, 100);
		animation.addFrame(heartAni_3, 100);
		animation.addFrame(heartAni_4, 100);
		animation.addFrame(heartAni_5, 100);

	}


	public void start() {
		animation.start();
	}

	
	public void update() {
		animation.update();
	}


	public void draw(Graphics2D g2) {
		g2.drawImage(animation.getImage(), x, y, 64, 64, null);
	}

}
