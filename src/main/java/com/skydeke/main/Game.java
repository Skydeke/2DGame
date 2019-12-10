package com.skydeke.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	/*
	 * Publics
	 */
	private static final long serialVersionUID = 8088455410901353842L;
	public static final int width = 640;
	public static final int height = width / 12*9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	public int realEnemys = 0;
	private Random r = new Random();
	
	public enum State{
		Menu,
		Help,
		Game;
	};
	
	public State gameState = State.Menu;
	
	public Game(){
		handler = new Handler();
		hud = new HUD();
		spawn = new Spawn(handler, hud);
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new Menu(this, handler));
		new Window(width, height, "Go!", this);
		if(gameState == State.Game){
			for (int i = 0; i < 1; i++) {
				handler.addObject(new BasicEnemy(r.nextInt(width- 16), r.nextInt(height- 16), ID.BasicEnemy, handler));
			}
			handler.addObject(new Player(width / 2 - 32, height / 2 - 32, ID.Player, handler));
		}
	}

	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new Game();
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: "+ frames);
				frames = 0;
			}
		}
		stop();
	}


	private void tick() {
		handler.tick();

		if(gameState == State.Game){
			hud.tick();
			spawn.tick();
		}else if(gameState == State.Menu || gameState == State.Help){
			menu.tick();
		}
	}


	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(150, 180, 80));
		g.fillRect(0, 0, width, height);
		handler.render(g);
		if(gameState == State.Game){
			hud.render(g);			
		}else if(gameState == State.Menu || gameState == State.Help){
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float val, float min, float max){
		if(val >= max){
			return val= max;
		}else if(val <= min){
			return val=min;
		}else{
			return val;
		}	
	}
}