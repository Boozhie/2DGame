package dev.code.tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.code.tilegame.display.Display;
import dev.code.tilegame.gfx.SpriteSheet;
import dev.code.tilegame.input.KeyManager;
import dev.code.tilegame.states.GameState;
import dev.code.tilegame.states.MenuState;
import dev.code.tilegame.states.State;
import dev.code.tilegame.gfx.Assets;
import dev.code.tilegame.gfx.GameCamera;
import dev.code.tilegame.gfx.ImageLoader;

public class Game implements Runnable {
	
	private Display display;
	
	private String title;
	private int width, height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
	
	//Input
	private KeyManager keyManager;
	
	
	//Camera
	
	private GameCamera gameCamera;
	
	
	//Handler
	
	private Handler handler;
	

	

	public Game(String title, int width, int height) {
		display = new Display(title, width, height);
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		
		
		
	}
	
	private void init() {
		
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		gameCamera = new GameCamera(handler, 0, 0);
		handler = new Handler(this);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(gameState);
		
		
	}
	
	
	
	private void update() {
		keyManager.update();
		
		
		if(State.getState() !=null)
			State.getState().update();

		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		//clear screen
		g.clearRect(0, 0, width, height);
		// Start draw
		
		if(State.getState() !=null)
			State.getState().render(g);
		
		// Stop draw
		bs.show();
		g.dispose();
		
		
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1){
			update();
			render();
			ticks++;
			delta--;
			}
			
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
}
