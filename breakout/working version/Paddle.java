import java.awt.*;
import java.awt.geom.*;

public class Paddle extends ColorShape{
	
	// location and size variables
	private static int speed;
	private static int xPos;
	private static final int yPos = Parameters.PADDLE_Y; //sets paddle at bottom of screen
	private static final int width = Parameters.PADDLE_WIDTH;
	private static final int height = Parameters.PADDLE_HEIGHT;

	private static final Rectangle2D.Double shape = new Rectangle2D.Double(Parameters.PADDLE_X,yPos,width,height);

	public Paddle() {
		super(shape);

		// set paddle color
		setFillColor(Color.BLACK);
		setBorderColor(Color.BLACK);

		// set paddle position and speed
		xPos = Parameters.PADDLE_X;
		speed = 0; //starts out not moving
	}

	public void move() {
		// move paddle
		xPos += speed;

		// stop the paddle from moving at the edges
		if(xPos >= (Parameters.WINDOW_WIDTH - Parameters.PADDLE_WIDTH)) {
			xPos = Parameters.WINDOW_WIDTH - Parameters.PADDLE_WIDTH;
		}
		if(xPos <= 0) {
			xPos = 0;
		}

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