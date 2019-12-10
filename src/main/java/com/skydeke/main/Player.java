package com.skydeke.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{

	public Random r = new Random();
	public Handler handler;
	public int realEnemys;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		//velX = r.nextInt(3);
		//velY = r.nextInt(3);
	}

	public void tick() {
		x += velX;
		y += velY;
		x = Game.clamp((int)x, 0, Game.width-38);
		y = Game.clamp((int)y, 0, Game.height-61);
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.blue, 32, 32, 0.04, handler));
		collision();
	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObj= handler.object.get(i);
			if(tempObj.getId() == ID.BasicEnemy || tempObj.getId() == ID.FastEnemy || tempObj.getId() == ID.SmartEnemy){
				if(getBounds().intersects(tempObj.getBounds())){
					HUD.health = HUD.health -2;
					//handler.removeObject(tempObj);
					//or HUD.helth -= 2;
				}
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//g.setColor(Color.GREEN);
		//g2d.draw(getBounds());
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}
