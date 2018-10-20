package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Key Listener for all player inputs
 */
public class KeyInput extends KeyAdapter {

	private Handler handler;
	
	/** Helps with stick-key bug */
	private boolean[] keyPressed;
	
	public KeyInput(Handler handler) { 
		this.handler = handler;
		keyPressed = new boolean[4];
		for(int x = 0; x < 4; x++) 
			keyPressed[x] = false;
	}
	
	public void keyPressed(KeyEvent e) { 
		int key = e.getKeyCode();
		for (int x = 0; x < handler.list.size(); x++) { 
			GameObject temp = handler.list.get(x);
			
			//-8 and 8 <-- normal values
			
			if (temp.getId() == ID.Player1) {
				//key events for player 1
				if (key == KeyEvent.VK_W) {
					temp.setVely(-12); keyPressed[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					temp.setVely(12); keyPressed[1] = true;
				}
			}
			if (temp.getId() == ID.Player2) { 
				//key events for player 2
				if (key == KeyEvent.VK_UP) {
					temp.setVely(-12); keyPressed[2] = true;
				}
				if (key == KeyEvent.VK_DOWN) {
					temp.setVely(12); keyPressed[3] = true;
				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
	}

	public void keyReleased(KeyEvent e) { 
		int key = e.getKeyCode();
		for (int x = 0; x < handler.list.size(); x++) { 
			GameObject temp = handler.list.get(x);
			
			if (temp.getId() == ID.Player1) { 
				if (key == KeyEvent.VK_W) { 
					keyPressed[0] = false;
				}
				if (key == KeyEvent.VK_S) { 
					keyPressed[1] = false;
				}
				
				if (!keyPressed[0] && !keyPressed[1]) { 
					temp.setVely(0);
				}
			}
			if (temp.getId() == ID.Player2) { 
				if (key == KeyEvent.VK_UP) { 
					keyPressed[2] = false;
				}
				if (key == KeyEvent.VK_DOWN) { 
					keyPressed[3] = false;
				}
				if (!keyPressed[2] && !keyPressed[3]) { 
					temp.setVely(0);
				}
			}
		}
	}
}