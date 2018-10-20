package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	/** Auto-generated */
	private static final long serialVersionUID = -2777549288168007767L;
	
	/** Holds all in-game objects */
	private Handler handler;
	
	/** Single thread that runs game, implementing Runnable allows thread to run */
	private Thread thread;
	
	/** Is game running boolean variable */
	private boolean running = false;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	/** Heads-up display */
	private HUD hud;
	
	/** State of the game */
	public enum STATE { 
		MENU, PLAY, HELP
	};
	STATE state;
	
	private Random r = new Random();
	
	/** Mouse Listener, waits for parts of the menu to be pressed */
	private Menu menu;
	
	/** Our sound stream loader */
	private Sounds mainMusic = new Sounds();
	
	/** Path to theme music */
	private String pathtoTheme = "mainmusic.wav";
	
	private Player player1, player2;
	
	/** Temporary menu balls */
	private Ball menuBall1 = null, menuBall2 = null;
	private Ball menuBall3 = null, menuBall4 = null;
	
	
	public Game() { 
		new Window(this);
		handler = new Handler();
		hud = new HUD(handler);
		this.addKeyListener(new KeyInput(handler));
		this.menu = new Menu(this, handler);
		this.addMouseListener(menu);

		this.state = STATE.MENU;
		
		if (state == STATE.PLAY) { 
			init();
		}
		
		//mainMusic.playSound(pathtoTheme);
		
		//working version of music player
		mainMusic.PlaySound(pathtoTheme);
	}
	
	/**
	 * Initialize all interactive true(non-menu) in-game items
	 */
	public void init() { 
		player1 = new Player(50, HEIGHT / 2, ID.Player1, handler, Color.red);
		player2 = new Player(WIDTH - 75, HEIGHT / 2,ID.Player2, handler, Color.blue);
		this.handler.addObject(player1);
		this.handler.addObject(player2);
		this.handler.addObject(new Ball(WIDTH / 2, HEIGHT / 2, ID.Ball, handler));
		this.removeMouseListener(menu);
	}
	
	public synchronized void start() { 
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
	 * Implementation necessary by <code>Runnable</code> interface
	 */
	public void run() { 
		//classic game loop, 
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int seconds = 0;
		
		while(running) { 
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) { 
				tick();
				delta--;
			}
			if(running) { 
				render();
				if (seconds == 30) { 
					//mainMusic.playSound(pathtoTheme);
					mainMusic.PlaySound(pathtoTheme);
					seconds = 0;
				}
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) { 
				timer += 1000;
				System.out.println("FPS: " + frames);
				seconds++;
				frames = 0;
			}
		}
		stop();
	}
	
	public synchronized void stop() { 
		try { 
			thread.join();
			running = false;
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public void tick() { 
		handler.tick();
		hud.tick();
	}
	
	private boolean var = true;
	//menu items need to be initialized
	private boolean aBallNeeded = true;
	//menu items need to be destroyed
	private boolean menuBallDeleted = false;
	//in game render
	public void render() { 
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) { 
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (state == STATE.MENU) 
		{
			g.setColor(Color.white);
			
			//play button dimensions
			g.drawRect(Game.WIDTH/2 - 100, 200, 200, 50);
			//help button dimensions
			g.drawRect(Game.WIDTH/2 - 100, 275, 200, 50);
			//exit button dimensions
			g.drawRect(Game.WIDTH/2 - 100, 350, 200, 50);
			
			g.setFont(new Font("Consolas", 1, 50));
			g.drawString("PLAY", Game.WIDTH/2 - 55, 240);
			g.drawString("HELP", Game.WIDTH/2 - 50, 315);
			g.drawString("EXIT", Game.WIDTH/2 - 50, 390);
			
			//title
			g.setFont(new Font("Consolas", 1, 70));
			g.drawString("Classic Pong", 94, 140);
			g.setFont(new Font("Consolas", Font.ITALIC, 12));
			g.drawString("by David Padron Castillo", 335, 165);
			
			//or in other words, menu items needed
			if (aBallNeeded) { 
				menuBall1 = new Ball(r.nextInt(WIDTH - 20), r.nextInt(HEIGHT - 20), ID.Ball, handler, r.nextInt(32)+10, r.nextInt(32)+10, false);
				menuBall1.setVelx(r.nextInt(16) - 8); menuBall1.setVely(r.nextInt(16)-8);
				menuBall1.setColor(Color.blue);
				this.handler.addObject(menuBall1);
				
				menuBall2 = new Ball(r.nextInt(WIDTH - 20), r.nextInt(HEIGHT - 20), ID.Ball, handler, r.nextInt(32)+10, r.nextInt(32)+10, false);
				menuBall2.setVelx(r.nextInt(16)-8); menuBall2.setVely(r.nextInt(16)-8);
				menuBall2.setColor(Color.magenta);
				this.handler.addObject(menuBall2);
				
				menuBall3 = new Ball(r.nextInt(WIDTH - 20), r.nextInt(HEIGHT - 20), ID.Ball, handler, r.nextInt(32)+10, r.nextInt(32)+10, false);
				menuBall3.setVelx(r.nextInt(16)-8); menuBall3.setVely(r.nextInt(16)-8);
				menuBall3.setColor(Color.green);
				this.handler.addObject(menuBall3);
				
				menuBall4 = new Ball(r.nextInt(WIDTH - 20), r.nextInt(HEIGHT - 20), ID.Ball, handler, r.nextInt(32)+10, r.nextInt(32)+10, false);
				menuBall4.setVelx(r.nextInt(16)-8); menuBall4.setVely(r.nextInt(16)-8);
				menuBall4.setColor(Color.orange);
				this.handler.addObject(menuBall4);
				
				
				aBallNeeded = false;
			}
			
		}
		if (state == STATE.PLAY)  { 
			if (var) { 
				init();
				var = false;
			}
			//in other words, menu items need to be deleted
			if (!menuBallDeleted) { 
				this.handler.removeObject(menuBall1);
				this.handler.removeObject(menuBall2);
				this.handler.removeObject(menuBall3);
				this.handler.removeObject(menuBall4);
				
				//reset Frames Processed
				Ball.counter = 0;
				menuBallDeleted = true;
			}
		}

		handler.render(g);
		if (state == STATE.PLAY) { 
			hud.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) { 
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else 
			return var;
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
