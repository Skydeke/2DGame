package com.skydeke.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class FastEnemy extends GameObject{

	public Random r = new Random();
	private Handler handler;
	
	public FastEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 8;
		velY = 9;
	}

	public void tick() {
		x += velX;
		y += velY;
		if (y <= 0 || y >= Game.height - 35) {
			velY *= -1;
		}
		if (x<= 0 || x >= Game.width - 35) {
			velX *= -1;
		}
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.pink, 16, 16, 0.08, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect((int)x, (int)y, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
