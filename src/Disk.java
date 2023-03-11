package src;

import java.awt.*;
import java.awt.geom.Rectangle2D;


public class Disk {
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;

	public Image disk;
	private int dy;		// increment to move along y-axis
	private final static int dx = 5;     // increment to move along x-axis
	private int x=0;
	private int y=100;
	public boolean left=false;

	public Disk(String file) {
		disk = ImageManager.loadImage(file);
	}

	public void setX(int x) {
		this.x = x;}

	public int getX(){
		return this.x;
	}


	public void moveDisk(int direction) {
		if (direction == 1) {
			this.x = x-dx;
		} else if (direction == 2) {
			this.x = x+dx;
		}
	}

	public void draw(Graphics2D g2){
		g2.drawImage(disk,x,y,WIDTH, HEIGHT, null);
	}

	//This method checks to see if the current image collides with the other image
	public boolean collisionCheck(Disk d2) {
		Rectangle2D.Double d1Rect = getBoundingRoundRectangle();
		Rectangle2D.Double d2Rect = d2.getBoundingRoundRectangle();
		boolean intersect = d1Rect.intersects(d2Rect);
		return intersect;
	}

	public Rectangle2D.Double getBoundingRoundRectangle() {
		return new Rectangle2D.Double (x, y, WIDTH, HEIGHT);
	}
}