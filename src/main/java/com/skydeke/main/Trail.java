package com.skydeke.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{
	
	private float alpha = 1;
	private Handler handler;
	private Color c;
	private int width;
	private int height;
	private double life;

	public Trail(int x, int y, ID id, Color c, int width, int height, double life,  Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.c = c;
		this.width = width;
		this.height = height;
		this.life = life;
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		if(alpha > life){
			alpha -= life -0.001;
		}else{
			handler.removeObject(this);
		}
	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(c);
		g.fillRect((int)x, (int)y, width, height);
		g2d.setComposite(makeTransparent(1));
		
	}
	
	public AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}

}
