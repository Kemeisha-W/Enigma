package src;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import static java.lang.Thread.sleep;

public class Disk {
	public static final int WIDTH = 115;
	public static final int HEIGHT = 115;
	public static final int ARC_WIDTH = 130;
	public static final int ARC_HEIGHT = 130;

	public RoundRectangle2D disk;
	public Color cFill;
	public Color cStroke;
	private int dy;		// increment to move along y-axis
	private int x;
	private int y=0;

	public Disk() {
		this.dy = 20;
		this.disk = new RoundRectangle2D.Double(x, y, WIDTH, HEIGHT,ARC_WIDTH,ARC_HEIGHT);
	}

	public void setX(int x) {this.x = x;}
	public void erase (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// erase bat by drawing a rectangle on top of it with the background colour
		g2.setColor (new Color(232, 142, 29));
		g2.fill (new Rectangle2D.Double (x, y-dy, WIDTH, HEIGHT+2));
	}


	public Canvas dropDisk(Canvas canvas, BoardSpace space, Boolean correct) throws InterruptedException {
		Graphics g= canvas.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		disk.setRoundRect(new RoundRectangle2D.Double(x, y, WIDTH, HEIGHT,ARC_WIDTH,ARC_HEIGHT));  // Set disk size and placement
		space.x = x;
		g2.setColor(cFill);
		g2.fill(disk);		// colour the disk
		sleep(100);
		while(!collisionCheck(space,canvas)){
			erase(g);
			y += dy;
			disk.setRoundRect(new RoundRectangle2D.Double(x, y, WIDTH, HEIGHT,ARC_WIDTH,ARC_HEIGHT));
			g2.setColor(cFill);
			g2.fill(disk);		// colour the disk
			sleep(100);
		}
		Rectangle2D.Double column = new Rectangle2D.Double(space.x, 0, Disk.WIDTH, 750);

		space.spaceFilled = true;
		if (correct){
			space.color = new Color(48,84,51);
		}else {
			space.color = new Color(102,6,4);
		}
		g2.setColor(space.color);
		g2.fill(column);
		space.hasDisk = true;
		return canvas;
	}



	//This method checks to see if the current disk collides with the boardspace
	//If there is a collision you fill the space with the correct color and record the space as filled
	private boolean collisionCheck(BoardSpace spaceData,Canvas canvas) {
		Rectangle2D.Double space = new Rectangle2D.Double(spaceData.x, 640, WIDTH, HEIGHT);
		Rectangle2D.Double diskArea = getBoundingRoundRectangle();
		return diskArea.intersects(space);
	}

	public Rectangle2D.Double getBoundingRoundRectangle() {
		return new Rectangle2D.Double (x, y, WIDTH, HEIGHT);
	}
}