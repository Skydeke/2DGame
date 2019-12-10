package com.skydeke.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject{

	public Random r = new Random();
	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = r.nextInt(2)+3;
		velY = r.nextInt(2)+3;
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
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.08, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
