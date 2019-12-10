package com.skydeke.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.management.monitor.GaugeMonitorMBean;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == Game.State.Menu){
			//Play
			if(mouseOver(mx, my,220, 130, 200, 64)){
				game.gameState = Game.State.Game;
				for (int i = 0; i < 1; i++) {
					handler.addObject(new BasicEnemy(r.nextInt(Game.width- 16), r.nextInt(Game.height- 16), ID.BasicEnemy, handler));
				}
				handler.addObject(new Player(Game.width / 2 - 32, Game.height / 2 - 32, ID.Player, handler));
			}
			
			
			//Quit
			if(mouseOver(mx, my, 220, 330, 200, 64)){
				System.exit(0);
			}
			
			//Help
			if(mouseOver(mx, my, 220, 230, 200, 64)){
				game.gameState = Game.State.Help;
			}
		}else if(game.gameState == Game.State.Help){
			if(mouseOver(mx, my, 220, 330, 200, 64)){
				game.gameState = Game.State.Menu;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < y + width){
			if (my > y && my < y + height) {
				return true;
			}else{
				return false;
			}
		}else {
			return false;
		}
	}

	public void render(Graphics g){
		if(game.gameState == Game.State.Help){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Use the Arrow Keys or WASD to move", 10, 200);
			g.drawString("the player to doge enemies", 10, 235);
			
			g.setColor(Color.white);
			g.drawString("Back", 280, 370);
			
			g.setColor(Color.ORANGE);
			g.drawRect(220, 330, 200, 64);
			
		}else if(game.gameState == Game.State.Menu){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Menu", 240, 70);
			
			g.setFont(fnt2);
			
			g.setColor(Color.white);
			g.drawString("Play", 280, 170);
			
			g.setColor(Color.white);
			g.drawString("Help", 280, 270);
			
			g.setColor(Color.white);
			g.drawString("Quit", 280, 370);
			
			
			g.setColor(Color.ORANGE);
			g.drawRect(220, 130, 200, 64);
			
			g.setColor(Color.ORANGE);
			g.drawRect(220, 230, 200, 64);
			
			g.setColor(Color.ORANGE);
			g.drawRect(220, 330, 200, 64);
		}
	}
	
	public void tick(){
		
	}
}
