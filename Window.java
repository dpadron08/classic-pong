package main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Simple Main Menu Window and Game window. Called in main
 * method of main <code>Game</code> class
 */
public class Window extends Canvas {

	private static final long serialVersionUID = -2797950736208732138L;
	
	public final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	public final String title = "Classic Pong";
	
	public Window(Game game) { 
		JFrame frame = new JFrame(title);
		
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		
		game.start();
	}
}