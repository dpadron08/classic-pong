package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	private int width = 25, height = 100;
	private Handler handler;
	private Color color;
	private Color playerhit = Color.white;
	
	public Player(int x, int y, ID id, Handler handler, Color color) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
	}
	
	@Override
	void tick() {
		x += velx;
		y += vely;
		
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 127);
	}

	@Override
	void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);	
	}

	/**
	 * Return the rectangle bounds to help with collision
	 * detection
	 */
	@Override
	Rectangle getBounds() {
		return new Rectangle(x, y, width, height+10);
	}
	
	public void revert(Graphics g) { 
		g.setColor(color);
	}
}