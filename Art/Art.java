import java.awt.Color;

public class Art {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		double len = .5;
		double y = 0;
		double x = len;
		drawDH(x, y, len, n);
	}

	public static void filledEllipse(double x, double y, double ma, double mi, Color c) {
		StdDraw.setPenColor(c);
		StdDraw.filledEllipse(x,y,ma,mi);
	}
	public static void filledTriangle(double x, double y, double len, Color c) {
		double h = len * Math.sin(Math.PI /3);
		double[] xc = {x, x + len/2, x - len/2};
		double[] yc = {y, y + h, y + h};
		StdDraw.setPenColor(c);
		StdDraw.filledPolygon(xc, yc);
	}
	public static void filledCircle(double x, double y, double r, Color c) {
		StdDraw.setPenColor(c);
		StdDraw.filledCircle(x,y,r);
	}

	public static void drawDH(double x, double y, double len, int n) {
		double h = len * Math.sin(Math.PI /3);
		double major = len/7;
		double major2 = len/30;
		double minor = h/2;
		double eh = minor;
		Color c1 = Color.RED;
		Color c2 = Color.ORANGE;
		Color c3 = Color.BLACK;
		Color c4 = Color.WHITE;

		if(n == 0) return;
		filledTriangle(x,y,len,c4);
		filledCircle(x,y + h/2,h/2,c1);
		filledEllipse(x, y + eh, major, minor,c2);
		filledEllipse(x,y + eh, major2, minor,c3);
		drawDH(x + len/2, y, len/2, n - 1);
		drawDH(x - len/2, y, len/2, n - 1);
		drawDH(x, y + h, len/2, n - 1);
		
	}
}