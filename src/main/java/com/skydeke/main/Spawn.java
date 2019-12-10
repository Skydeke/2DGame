package com.skydeke.main;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private float scoreKeep = 0;
	private float level = 0;
	private Random r = new Random();
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		scoreKeep++;
		if(scoreKeep >= 500){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			//for (int i = 0; i < hud.getLevel(); i++) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.width- 16), r.nextInt(Game.height- 16), ID.BasicEnemy, handler));
			//}
			level = hud.getLevel();
			if(level >= 3){
				handler.addObject(new SmartEnemy(r.nextInt(Game.width- 16), r.nextInt(Game.height- 16), ID.SmartEnemy, handler));
			}else if(level >= 4){
				handler.addObject(new FastEnemy(r.nextInt(Game.width- 16), r.nextInt(Game.height- 16), ID.FastEnemy, handler));
			}
		}
	}
}
