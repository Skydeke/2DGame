package com.skydeke.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class SmartEnemy extends GameObject{

	public Random r = new Random();
	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super((int)x, (int) y, id);
		this.handler = handler;
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				player = handler.object.get(i);
			}
		}
	}

	public void tick() {
		x += velX;
		y += velY;
		float diffX = x - player.getX() - 32;
		float diffY = x - player.getY() - 32;
		float distance = (float) Math.sqrt( (x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()) );
		
		velX = (float) ((-1.0 / distance) * diffX);
		velY = (float) ((-1.0 / distance) * diffY);
		
		if (y <= 0 || y >= Game.height - 35) {
			velY *= -1;
		}
		if (x<= 0 || x >= Game.width - 35) {
			velX *= -1;
		}
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.LIGHT_GRAY, 16, 16, 0.08, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((int)x, (int)y, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
