// Andrew Stekar
// Final Project - Flappy Bird
// Tues., Nov. 10/2020.

package final_project;

import hsa_ufa.Console;

import java.awt.*;
import java.util.Random;

public class Final_Class {

	static Console c = new Console(700, 500);

	public static void main(String[] args) throws InterruptedException {
// declaring images
		Image ground;
		Image pipe;
		Image bird1;
		Image bird2;
		Image bird3;
		Image bird4;
		Image gOver;
		Image none;
		Image bronze;
		Image silver;
		Image gold;
		Image platinum;
		Image clouds;
		Image towers;
		Image bushes;
		Image title;

// assigning image values		
		ground = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/flappy ground.png"));
		pipe = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/pipe2.png"));
		bird1 = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/bird.png"));
		bird2 = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/bird2.png"));
		bird3 = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/bird4.png"));
		bird4 = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/bird6.png"));
		gOver = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/End screen4.png"));
		none = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/noMedal.png"));
		bronze = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/bronzeMedal.png"));
		silver = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/silverMedal.png"));
		gold = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/goldMedal.png"));
		platinum = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/platinumMedal.png"));
		clouds = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/clouds.png"));
		towers = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/buildings.png"));
		bushes = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/grass.png"));
		title = Toolkit.getDefaultToolkit()
				.getImage(c.getClass().getClassLoader().getResource("final_project/title2.png"));

// assigning variables
		while (true) {

			int x[] = { 705, 705, 905, 905, 1105, 1105, 1305, 1305, 1505, 1505 };
			int y[] = { 350, -260, 230, -380, 300, -310, 250, -360, 300, -310 };
			int w[] = { 58, 58, 58, 58, 58, 58, 58, 58, 58, 58 };
			int h[] = { 500, 500, 500, 500, 500, 500, 500, 500, 500, 500 };
			Random rng = new Random();
			int vy = 0;
			int birdY = 210;
			int cloudX = -500;
			int towerX = -500;
			int bushX = -500;
			boolean up_not_pressed = true;
			int AY = 1;
			int groundX = -100;
			int hit = 0;
			int score = 0;
			int v = 1;
			int collX = 700;
			int j = 0;

			while (v == 1) {

				synchronized (c) {

// drawing images
					draw_stuff(clouds, cloudX, towers, towerX, bushes, bushX, pipe, x, y, w, h, ground, groundX);
					if (c.isKeyDown(Console.VK_UP) && (birdY <= 391) && (hit == 0))
						c.drawImage(bird2, 194, birdY - 5, 42, 38);
					else if ((birdY <= 391) && (hit == 0)) {
						c.drawImage(bird1, 194, birdY - 5, 42, 38);
					}
				}

// displaying score		
				if (j == 1) {
					c.setColor(Color.black);
					c.setFont(new Font("Arial Black", Font.BOLD, 47));
					c.drawString("" + score * 1 / 2 + "", 320, 40);
				}

// beginning screen
				if (j == 0) {
					c.drawImage(title, 107, 70, 480, 100);
					c.setColor(Color.black);
					c.setFont(new Font("Arial Black", Font.BOLD, 25));
					c.drawString("PRESS 'UP ARROW' TO JUMP", 135, 360);
					c.drawString("HOLD TO GLIDE", 223, 400);
				}

				Thread.sleep(25);

// collision detection
				for (int i = 0; i < x.length; i += 1) {
					if ((200 < (x[i] + w[i])) && (x[i] < (200 + 30)) && (y[i] < (birdY + 30))
							&& (birdY < (y[i] + h[i]))) {
						hit = 1;
					}
				}
				if ((200 < (collX)) && (collX < (200 + 30)) && (-5000 < (birdY + 30)) && (birdY < (-5000 + 4800))) {
					hit = 1;
				}

// falling animation
				while (birdY <= 390 && hit == 1) {
					draw_stuff(clouds, cloudX, towers, towerX, bushes, bushX, pipe, x, y, w, h, ground, groundX);
					c.drawImage(bird4, 194, birdY - 20, 35, 45);
					birdY += vy;
					vy += AY;
					if (vy > 35)
						vy = 35;
					c.setColor(Color.black);
					c.setFont(new Font("Arial Black", Font.BOLD, 47));
					c.drawString("" + score * 1 / 2 + "", 320, 40);
					Thread.sleep(25);
				}

//bird in ground screen	
				if (birdY >= 390) {
					draw_stuff(clouds, cloudX, towers, towerX, bushes, bushX, pipe, x, y, w, h, ground, groundX);
					c.drawImage(bird3, 194, 387, 35, 38);
					c.setColor(Color.black);
					c.setFont(new Font("Arial Black", Font.BOLD, 47));
					c.drawString("" + score * 1 / 2 + "", 320, 40);
					Thread.sleep(500);

// losing screen
					draw_stuff(clouds, cloudX, towers, towerX, bushes, bushX, pipe, x, y, w, h, ground, groundX);
					c.drawImage(bird3, 194, 387, 35, 38);
					c.drawImage(gOver, -140, 50, 1000, 450);
					c.setColor(Color.black);
					c.setFont(new Font("Arial Black", Font.BOLD, 60));

// medals and score display
					if (score < 20) {
						c.drawString("" + score * 1 / 2 + "", 478, 270);
						c.drawImage(none, 156, 220, 80, 80);
					} else if (score < 40 && score >= 20) {
						c.drawString("" + score * 1 / 2 + "", 460, 270);
						c.drawImage(bronze, 156, 220, 80, 80);
					} else if (score < 60 && score >= 40) {
						c.drawString("" + score * 1 / 2 + "", 460, 270);
						c.drawImage(silver, 156, 220, 80, 80);
					} else if (score < 80 && score >= 60) {
						c.drawString("" + score * 1 / 2 + "", 460, 270);
						c.drawImage(gold, 156, 220, 80, 80);
					} else {
						c.drawString("" + score * 1 / 2 + "", 460, 270);
						c.drawImage(platinum, 156, 220, 80, 80);
					}
					Thread.sleep(700);
					c.setFont(new Font("Arial Black", Font.BOLD, 15));
					c.drawString("PRESS ANY KEY TO PLAY AGAIN", 200, 330);

// restart game
					c.getChar();
					v = 2;
				}

// moving images
				groundX -= 4;
				bushX -= 3;
				cloudX -= 1;
				towerX -= 2;
				if ((collX > 0) && (j == 1))
					collX = collX - 4;
				for (int i = 0; i < x.length; ++i) {
					if (j == 1)
						x[i] -= 4;
					if (x[i] <= -200) {
						x[i] = 800;
						if ((i % 2 == 0) && (j == 1) || (j == 1) && (i == 0)) {
							y[i] = rng.nextInt(220) + 180;
						} else
							y[i] = y[i - 1] - 610;
					}
				}

// jumping
				if (c.isKeyDown(Console.VK_UP) && up_not_pressed) {
					vy = -10;
					up_not_pressed = false;
				}
				if (!c.isKeyDown(Console.VK_UP))
					up_not_pressed = true;

// start game
				if (c.isKeyDown(Console.VK_UP))
					j = 1;

// Do "gravity"
				if (j == 1) {
					birdY += vy;
					vy += AY;
				}

// Limit maximum falling speed
				if (vy > 35)
					vy = 35;

// gliding
				if (c.isKeyDown(Console.VK_UP) && (vy > 1))
					vy = 1;

// resetting values
				if (groundX <= -190)
					groundX = -100;
				if (bushX <= -1296)
					bushX = -500;
				if (cloudX <= -1296)
					cloudX = -500;
				if (towerX <= -1296)
					towerX = -500;

// score		
				for (int i = 0; i < x.length; ++i) {
					if ((x[i] < 150) && (x[i] > 146))
						score++;
				}
			}
		}
	}

	/**
	 * draws all background images and pipes.
	 * 
	 * @param cs the cloud image.
	 * @param cx the x-position of the cloud.
	 * @param t  the image of the buildings.
	 * @param tx the x-position of the buildings image.
	 * @param b  the image of the bushes.
	 * @param bx the x-position of the bushes.
	 * @param p the image of the pipes.
	 * @param x array of x-positions of the pipes.
	 * @param y array of the y-positions if the pipes.
	 * @param w array of the widths of the pipes
	 * @param h array of the heights of the pipes.
	 * @param g  the ground image.
	 * @param gx the x-position of the ground.
	 */
	static void draw_stuff(Image cs, int cx, Image t, int tx, Image b, int bx, Image p, int x[], int y[], int w[],
			int h[], Image g, int gx) {
		c.clear();
		c.setColor(new Color(130, 215, 209));
		c.fillRect(0, 0, 1000, 500);
		c.drawImage(cs, cx, -70, 1700, 700);
		c.drawImage(cs, cx + 796, -70, 1700, 700);
		c.drawImage(t, tx, -70, 1700, 700);
		c.drawImage(t, tx + 796, -70, 1700, 700);
		c.drawImage(b, bx, -80, 1700, 700);
		c.drawImage(b, bx + 796, -80, 1700, 700);
		for (int i = 0; i < x.length; ++i) {
			c.drawImage(p, (x[i] - 146), (y[i] - 82), (w[i] + 642), (h[i] + 88));
		}
		c.drawImage(g, gx, 425, 1000, 120);
	}
}
