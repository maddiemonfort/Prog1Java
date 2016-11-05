import java.awt.geom.*;

public class Brick extends ColorShape {
		private int xPos = 0;
		private int yPos = 0;
		private int width = 0;
		private int height = 0;
		private Rectangle2D.Double shape;

		// constructor
		public Brick(int x, int y, int w, int h) {
			super(new Rectangle2D.Double(x, y, w, h));
			
			//set brick x, y, width, and height
	
			// update shape
			shape.setRect(xPos, yPos, width, height);
		}

		public int getX() {
			return xPos;
		}

		public Rectangle2D.Double getShape() {
			return shape;
		}
	}