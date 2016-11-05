import java.awt.geom.*;
import java.awt.*;

public class Ball extends ColorShape {
	
	// location and size variables
	private int xPos;
	private int yPos;
	private int xSpeed;
	private int ySpeed;
	private static final int height = 0;
	private static final int width = 0;
	private static final Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, width, height);

	// constructor
	public Ball() {
		super(shape);

		// set ball color
		super.setFillColor(Color.RED);
		super.setBorderColor(Color.RED);
		
		// set initial values for x and y position and speed
	}

	public void move() {
		// move ball
		
		// detect if ball should bounce off an edge

		// update shape to new values
		shape.setFrame(xPos, yPos, width, height);
	}

	public void setXspeed(int newSpeed) {
		xSpeed = newSpeed;
	}

	public void setYspeed(int newSpeed) {
		ySpeed = newSpeed;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Ellipse2D.Double getShape() {
		return shape;
	}
}