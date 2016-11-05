import java.awt.*;
import java.awt.geom.*;

public class Paddle extends ColorShape{
	
	// location and size variables
	private static int speed;
	private static int xPos;
	private static final int yPos = 0;
	private static final int width = 0;
	private static final int height = 0;

	private static final Rectangle2D.Double shape = new Rectangle2D.Double(0,yPos,width,height);

	public Paddle() {
		super(shape);

		// set paddle color
		setFillColor(Color.BLACK);
		setBorderColor(Color.BLACK);

		// set paddle position and speed
	}

	public void move() {
		// move paddle
		
		// stop the paddle from moving at the edges
		
		// update shape
		shape.setRect(xPos, yPos, width, height);
	}

	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return xPos;
	}

	public Rectangle2D.Double getShape() {
		return shape;
	} 

}