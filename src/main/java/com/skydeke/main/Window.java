package com.skydeke.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	/**
	 * Publics
	 */
	private static final long serialVersionUID = 4352048917063661635L;
	
	public Window(int with, int height, String title, Game game){
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(with, height));
		frame.setMaximumSize(new Dimension(with, height));
		frame.setMinimumSize(new Dimension(with, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
