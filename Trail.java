package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Produces simulated trail in former game object positions
 */
public class Trail extends GameObject {

	/** Controls how long trail will last (life)*/
	private float alpha = 1;
	private float life;
	
	private Handler handler;
	private Color color;
	private int width, height;
	// life = 0.001 - 0.1
	
	public Trail(float x, float y, ID id, Color color, Handler handler, int width, int height, float life) {
		super((int)x, (int)y, id);
		this.handler = handler;
		this.color = color; 
		this.width = width; this.height = height;
		this.life = life;
	}

	@Override
	public void tick() {
		if (alpha > life) { 
			alpha -= life - 0.001f;
		} else { 
			handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);
		
		g2d.setComposite(makeTransparent(1));
		
	}
	
	private AlphaComposite makeTransparent(float alpha) { 
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	/**
	 * Not allowed to cause collision detection
	 * systems to go activate
	 */
	@Override
	public Rectangle getBounds() {
		return null;
	}
}