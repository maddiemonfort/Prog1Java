import java.awt.geom.*;
import java.awt.*;

public class Brick extends ColorShape {
		private int xPos;
		private int yPos;
		private int width = Parameters.BRICK_WIDTH;
		private int height = Parameters.BRICK_HEIGHT;
		private Rectangle2D.Double shape;
		private Color color = new Color(211,211,211);

		// constructor
		public Brick(int x, int y, int w, int h) {
			super(new Rectangle2D.Double(x, y, w, h));
			
			//set brick x, y, width, and height
			xPos = x;
			yPos = y;
			width = w;
			height = h;

			// update shape
			super.setFillColor(Color.WHITE);
			super.setBorderColor(Color.WHITE);
			shape = (Rectangle2D.Double)super.shape;
			shape.setRect(xPos, yPos, width, height);
		}

		public int getX() {
			return xPos;
		}

		public Rectangle2D.Double getShape() {
			return shape;
		}
	}