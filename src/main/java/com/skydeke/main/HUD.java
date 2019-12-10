package com.skydeke.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static float health = 100;
	private float greenVal = 255;
	private float score = 0;
	private float level = 1;
	
	public void tick(){
		health = Game.clamp(health, 0, 100);
		greenVal = Game.clamp(greenVal, 0, 255);
		greenVal = health *2;
		score++;
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, (int) greenVal, 0));
		g.fillRect(15, 15, (int) (health * 2), 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + score, 14, 68);
		g.drawString("Level:  " + level, 14, 80);
	}

	public float getLevel() {
		return level;
	}

	public void setLevel(float f) {
		this.level = f;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
}
