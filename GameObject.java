package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 
 * Abstract parent class of all in-game interactive objects
 *
 */
public abstract class GameObject {

	protected int x, y;
	protected int velx, vely;
	protected ID id;
	protected Color color = Color.white;
	
	public GameObject(int x, int y, ID id) { 
		this.x = x; this.y = y;
		this.id = id;
	}
	
	abstract void tick();
	abstract void render(Graphics g);
	abstract Rectangle getBounds();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelx() {
		return velx;
	}

	public void setVelx(int velx) {
		this.velx = velx;
	}

	public int getVely() {
		return vely;
	}

	public void setVely(int vely) {
		this.vely = vely;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	public Color getColor() { 
		return color;
	}
}
