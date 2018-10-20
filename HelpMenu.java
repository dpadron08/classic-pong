package main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Simple Help Menu
 */
public class HelpMenu extends JFrame {
	
	private static final long serialVersionUID = 1383983599789045742L;
	private String helpMessage;
	private JLabel label;
	
	public HelpMenu() {
		setAlwaysOnTop(true);
		setTitle("Help"); 
		this.setSize(new Dimension(400, 80));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.requestFocus();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		helpMessage = " For player 1 - (W - UP) (S - DOWN). For player 2 - (use arrow keys)\n Navigate the ball "
				+ "to the opponent's goal to gain points.\n Watch out for spontaneous changes in "
				+ "the direction of the ball.";
		
		label = new JLabel();
		getContentPane().add(label, BorderLayout.CENTER);
		
		JTextArea txtrForPlayer = new JTextArea();
		txtrForPlayer.setWrapStyleWord(true);
		txtrForPlayer.setToolTipText("Help");
		getContentPane().add(txtrForPlayer, BorderLayout.CENTER);
		txtrForPlayer.setText(helpMessage);	
	}	
}
