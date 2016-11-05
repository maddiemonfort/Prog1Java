import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Breakout {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Parameters.WINDOW_WIDTH,Parameters.WINDOW_HEIGHT);
        frame.setTitle("Breakout");
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.setVisible(true);
	}

	private static class GamePanel extends JPanel {
		
		Ball ball;
		Paddle paddle;
		BrickConfiguration bconfig;
		Timer timer;
		boolean isDead;
		boolean winner;
		boolean paused;
		int lives;
		int level;
		int score;
		int bricksHit;
		ImageIcon background;

		public GamePanel() {
			super();

			// call initializeGameObjects()
			initializeGameObjects();
			level = 1;
			score = 0;

			//create next level
			
			
			// add PaddleMover as a keyListener
			addKeyListener(new PaddleMover());

			setFocusable(true);		
		}

		public void initializeGameObjects() {
			// instantiate ball, paddle, and brick configuration
			paddle = new Paddle();
			ball = new Ball();
			bconfig = new BrickConfiguration();
			background = new ImageIcon("breakout.jpg");
			isDead = false;
			winner = false;
			paused = false;
			lives = 3;
			bricksHit = 0;

			// set up timer to run GameMotion() every 10ms
			timer = new Timer(10, new GameMotion());
			timer.start();
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;

			// paint ball,paddle, and brick configuration
			g2.drawImage(background.getImage(),0,0,null);
			paddle.paint(g2);
			ball.paint(g2);
			bconfig.paint(g2);

			//paint lives
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Aharoni", Font.BOLD, 20));
			g2.drawString("Lives: " + lives, 500, 400);

			//paint score
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Aharoni", Font.BOLD, 20));
			g2.drawString("Score: " + bricksHit, 40, 400);

			//paint level
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Ar DESTINE", Font.BOLD, 20));
			g2.drawString("Level: " + level, 240, 20);

			//Lost game
			if(isDead) {
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("AR DESTINE", Font.PLAIN, 50));
				g2.drawString("Game Over!", 180, 300);
				timer.stop();
			}
			//Won game
			if(winner) {
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("AR DESTINE", Font.PLAIN, 50));
				g2.drawString("You Won!", 180, 250);
				timer.stop();
			}

			//paused game
			if(paused) {
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Aharoni", Font.BOLD, 25));
				g2.drawString("Paused. Press Q to continue", 160, 255);
				timer.stop();
			}
			
		}

		private class GameMotion implements ActionListener {
			public GameMotion() {

			} 

			public void actionPerformed(ActionEvent evt) {
				//move ball automatically
				ball.move();

				//move paddle according to key press
				paddle.move();

				//check if the ball hits the paddle or a brick
				checkForHit();

				//check if dead
				checkForDead();

				//check if won
				checkForWin();
				
				//call repaint
				repaint();
			}
		}


		private class PaddleMover implements KeyListener {
			public void keyPressed(KeyEvent evt) {
				// change paddle speeds for left and right key presses
				int key = evt.getKeyCode();
				if (key == KeyEvent.VK_LEFT) {
					paddle.setSpeed(-Parameters.PADDLE_SPEED);
				}
				if(key == KeyEvent.VK_RIGHT) {
					paddle.setSpeed(Parameters.PADDLE_SPEED);
				}
				if(key == KeyEvent.VK_P) {
					paused = true;
				}
				if(key == KeyEvent.VK_Q) {
					paused = false;
					timer.start();
				}

			}
			public void keyReleased(KeyEvent evt) {
				// set paddle speed to 0
				paddle.setSpeed(0);
			}
			public void keyTyped(KeyEvent evt) {}
		}

		public void checkForDead() {
			if(lives == 0) {
				isDead = true;
			}
			else {
				if(ball.getShape().intersects(new Rectangle2D.Double(0,500,600,10))) {
					lives--;
					ball.setX(Parameters.BALL_X);
					ball.setY(Parameters.BALL_Y);
					ball.setXspeed(Parameters.BALL_SPEED);
					ball.setYspeed(Parameters.BALL_SPEED);
				}
						
			}
		}

		public void checkForWin() {
			if(bricksHit == bconfig.getCols()*bconfig.getRows()) {
				winner = true;
			}
		}

		public void checkForHit() {
			
			// change ball speed when ball hits paddle
			if (ball.getShape().intersects(paddle.getShape())) {
				int leftSide = paddle.getX();
				int middleLeft = paddle.getX() + (int)(paddle.getWidth()/3);
				int middleRight = paddle.getX() + (int)(2*paddle.getWidth()/3);
				int rightSide = paddle.getX() + paddle.getWidth();

				if ((ball.getX() >= leftSide) && (ball.getX() < middleLeft)) {
					// change ball speed
					ball.setXspeed(-1 - (int)Math.abs(ball.getXspeed()));
					ball.setYspeed(-1 - ball.getYspeed());
				}
				if ((ball.getX() >= middleLeft) && (ball.getX() <= middleRight)) {
					// change ball speed
					ball.setXspeed(0);
					ball.setYspeed(-Parameters.BALL_SPEED);
				}
				if ((ball.getX() > middleRight) && (ball.getX() <= rightSide)) {
					// change ball speed
					ball.setXspeed(1 + (int)Math.abs(ball.getXspeed()));
					ball.setYspeed(-1 - ball.getYspeed());
				}
			}

			// change ball speed when ball hits brick
			for (int i = 0; i < bconfig.getCols(); i++) {
				for (int j = 0; j < bconfig.getRows(); j++) {
					if (bconfig.exists(i,j)) {
						if (ball.getShape().intersects(bconfig.getBrick(i,j).getShape())) {
							Point ballLeft = new Point((int)ball.getShape().getX(), (int)(ball.getShape().getY() + ball.getShape().getHeight()/2));
							Point ballRight = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()), (int)(ball.getShape().getY() + ball.getShape().getHeight()/2));
							Point ballTop = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()/2), (int)ball.getShape().getY());
							Point ballBottom = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()/2), (int)(ball.getShape().getY() + ball.getShape().getHeight()));
							if (bconfig.getBrick(i,j).getShape().contains(ballLeft)) {
								//change ball speed
								ball.setXspeed(ball.getXspeed());
							}
							else if(bconfig.getBrick(i,j).getShape().contains(ballRight)) {
								//change ball speed
								ball.setXspeed(-ball.getXspeed());
							}
							if (bconfig.getBrick(i,j).getShape().contains(ballTop)) {
								//change ball speed
								ball.setYspeed(ball.getYspeed());
							}
							else if (bconfig.getBrick(i,j).getShape().contains(ballBottom)) {
								//change ball speed
								ball.setYspeed(-ball.getYspeed());
							}
							 //remove brick
							bconfig.removeBrick(i,j);
							bricksHit++;
						}
					}
				}
			}
		}
	}
}


