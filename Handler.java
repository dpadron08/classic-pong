package main;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Holds all interactive game objects and can update(tick) 
 * and render objects
 */
public class Handler {

	public ArrayList<GameObject> list = new ArrayList<GameObject>();
	
	public void tick() { 
		for (int x = 0; x < list.size(); x++) { 
			GameObject temp = list.get(x);
			temp.tick();
		}
	}
	
	public void render(Graphics g) { 
		for (int x = 0; x < list.size(); x++) { 
			GameObject temp = list.get(x);
			temp.render(g);
		}
	}
	
	public void addObject(GameObject a) { 
		this.list.add(a);
	}
	public void removeObject(GameObject a ) { 
		this.list.remove(a);
	}
}
