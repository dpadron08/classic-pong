package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game.STATE;

/**
 * Mouse listener for main menu
 */
public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	
	public Menu(Game game, Handler handler) {
		this.game = game;
	}

	public void mousePressed(MouseEvent e) { 
		int mx = e.getX();
		int my = e.getY();
		
		if (mouseOver(mx, my, Game.WIDTH/2 - 100, 200, 200, 50)) { 
			game.state = STATE.PLAY;
		}
		if (mouseOver(mx, my, Game.WIDTH/2 - 100, 275, 200, 50)) { 
			new HelpMenu();
		}
		if (mouseOver(mx, my, Game.WIDTH/2 - 100, 350, 200, 50)) { 
			System.exit(0);
		}
	}
	
	public void mouseReleased(MouseEvent e) { 
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) { 
		return ( (x < mx && mx < x+width) && (y < my && my < y+height) ); 
	}
}