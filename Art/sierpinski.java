import java.awt.Color;

public class sierpinski {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		double len = .5;
		double y = 0;
		double x = len;
		drawSierpinski(x, y, len, n);
	}
	public static void filledTriangle(double x, double y, double len) {
		double h = len * Math.sin(Math.PI /3);
		double[] xc = {x, x + len/2, x - len/2};
		double[] yc = {y, y + h, y + h};
		
		StdDraw.filledPolygon(xc, yc);
	}
	public static void drawSierpinski(double x, double y, double len, int n) {
		double h = len * Math.sin(Math.PI /3);
		if(n == 0) return;
		filledTriangle(x,y,len);
		drawSierpinski(x + len/2, y, len/2, n - 1);
		drawSierpinski(x - len/2, y, len/2, n - 1);
		drawSierpinski(x, y + h, len/2, n - 1);
		
	}
}