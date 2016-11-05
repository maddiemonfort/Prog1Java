import java.awt.geom.*;
import java.awt.*;

public class Ball extends ColorShape {
	
	// location and size variables
	private int xPos;
	private int yPos;
	private int xSpeed;
	private int ySpeed;
	private static final int diameter = Parameters.BALL_DIAMETER;
	private static final Ellipse2D.Double shape = new Ellipse2D.Double(Parameters.BALL_X, Parameters.BALL_Y, diameter, diameter);

	// constructor
	public Ball() {
		super(shape);

		// set ball color
		super.setFillColor(Color.GRAY);
		super.setBorderColor(Color.BLACK);
		
		// set initial values for x and y position and speed
		xPos = Parameters.BALL_X;
		yPos = Parameters.BALL_Y;
		xSpeed = Parameters.BALL_SPEED;
		ySpeed = Parameters.BALL_SPEED;
	}

	public void move() {
		// move ball
		xPos += xSpeed;
		yPos += ySpeed;

		// detect if ball should bounce off an edge
		if(xPos >= (Parameters.WINDOW_WIDTH - Parameters.BALL_DIAMETER - 5) || xPos <= 0) {
			xSpeed = -xSpeed;
		}
		if(yPos <= 0) {
			ySpeed = -ySpeed;
		}

		// update shape to new values
		shape.setFrame(xPos, yPos, diameter, diameter);
	}

	public void setXspeed(int newSpeed) {
		xSpeed = newSpeed;
	}

	public void setYspeed(int newSpeed) {
		ySpeed = newSpeed;
	}

	public void setX(int newX) {
		xPos = newX;
	}

	public void setY(int newY) {
		yPos = newY;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public int getXspeed() {
		return xSpeed;
	}

	public int getYspeed() {
		return ySpeed;
	}

	public int getWidth() {
		return diameter;
	}

	public int getHeight() {
		return diameter;
	}

	public Ellipse2D.Double getShape() {
		return shape;
	}
}