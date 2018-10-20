package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject {

	public final int width, height;
	private Handler handler;
	
	/*
	 * Counts tick updates of handler, handles when ball 
	 * changes direction
	 */
	public static int counter = 0;
	
	private Color color = Color.yellow;
	private Sounds sounds = new Sounds();
	
	/*
	 *path to sound file 
	 */
	String pathTohit = "playerhit5.wav";
	
	public boolean hasEffects = true;
	
	public Ball(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		//velx = vely = 5 normal values
		velx = vely = 10;
		width = height = 8;
		
	}
	public Ball(int x, int y, ID id, Handler handler, int width, int height, boolean hasEffects) { 
		super(x, y, id);
		this.handler = handler;
		velx = vely = 5;
		this.width = width; this.height = height;
		this.hasEffects = hasEffects;
	}

	@Override
	void tick() {
		counter++;
		x += velx;
		y += vely;
		
		
		/*
		 * Forces ball to bounce off walls when in contact
		 */
		if (y <= 2 || y >= Game.HEIGHT - 32) {
			vely *= -1;
		}
		
		if (x <= 0 || x >= Game.WIDTH - 20) { // -32
			velx *= -1;
		}
		//checks if ball has collided with playerObject in frame,
		//if so, changes ball color 
		collision();
		
		/*
		 * Make ball spontaneously change direction
		 */
		if (counter %50 ==0 && counter >=50) { //100
			vely *= -1;
		}
		if (counter %100 ==0 && counter >=100) { //250
			vely *= -1;
		}
		
		/** ONLY FOR HARD DIFFICULTY  */
		if (counter % 20 ==0 && counter >=20) { //523
			velx *= -1;
			vely *= -1;
		}
		if (counter == Integer.MAX_VALUE) { 
			counter = 0;System.out.println("MAX COUNTER!!");
		}	
		if (this.y <= 0) { 
			vely = 5;
		}
		handler.addObject(new Trail(x, y, ID.Trail, color, handler, width, height, 0.09f));
	}
	
	
	/**
	 * Method checks if  ball collided with player, if so : <p>-makes ball bounce off,
	 * <br>-changes the color of the ball,<br>-increments the amount of collisions in the HUD,
	 * and <br>-plays the player hit sound. <p>Path to file found in <code>String</code> variable
	 * <code>pathTohit</code>
	 */
	private void collision() { 
		for (int x = 0; x < handler.list.size(); x++) { 
			
			GameObject temp = handler.list.get(x);
			if (temp.getId() == ID.Player1 || temp.getId() == ID.Player2) { 
				if (this.getBounds().intersects(temp.getBounds())) { 
					velx *= -1;
					
					if (temp.getId() == ID.Player1) {
						color = Color.yellow;
					}
					if (temp.getId() == ID.Player2) {
						color = Color.green;
					}
					HUD.incrementHits();
					
					//sounds.playSound(pathTohit);
					sounds.PlaySound(pathTohit);
				}
			}
		}		
	}

	@Override
	void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	public int getCounter() { 
		return counter;
	}
	
	public void setColor(Color color) { 
		this.color = color;
	}
	
	@Override
	Rectangle getBounds() {
		return (new Rectangle(x, y, width, height+10));
	}
}
