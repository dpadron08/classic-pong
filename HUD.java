package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	private Handler handler;
	
	/** Number of collisions with players */
	private static int hits = 0;
	
	private int score1 = 0, score2 = 0;
	
	public HUD(Handler handler) { 
		this.handler = handler;
	}
	
	public void tick() {
		
		for (int x = 0; x < handler.list.size(); x++) { 
			GameObject temp = handler.list.get(x);
			if (temp.getId() == ID.Ball) { 
				if (temp.getX() == 0 &&  ((Ball)temp).hasEffects) { this.incrementScore2(); } 
				if (temp.getX() == Game.WIDTH - 40 && temp.getVelx() >= 0 && ((Ball)temp).hasEffects) { this.incrementScore1(); } // -40
			}
		}
	}
	
	public void render(Graphics g) { 
		g.setColor(Color.white);
		g.drawLine(Game.WIDTH / 2, 0, Game.WIDTH / 2, Game.HEIGHT);
		
		g.setFont(new Font("Consolas", Font.PLAIN, 40));
		g.drawString(score1+"", Game.WIDTH / 2 - 65, 50);
		g.drawString(score2+"", Game.WIDTH / 2 + 35, 50);
		
		g.setFont(new Font("Consolas", Font.PLAIN, 12));
		g.drawString("Collisions : " + hits, Game.WIDTH/2 + 2, 80);
		g.drawString("Score : " + Ball.counter, Game.WIDTH/2 + 2, 95);
	}
	public void incrementScore1() { 
		this.score1++;
	}
	public void incrementScore2() { 
		this.score2++;
	}
	public static void incrementHits() { 
		hits++;
	}
}