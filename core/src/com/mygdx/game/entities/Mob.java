package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Mob extends Image
{
	private final static int WIDHT = 80;
	private final static int HEIGHT = 80;
	
	private final static int STARTING_X = -100; //TODO this should be current 
	private final static int STARTING_Y = 500;  // stage base (X,Y)

	public Mob()
	{
		super(new Texture("badlogic.jpg"));
		
		this.setOrigin(WIDHT / 2, HEIGHT / 2);
		this.setSize(WIDHT, HEIGHT);
		
		this.setPosition(STARTING_X, STARTING_Y);
		
		//on click show mob info
	}
	
	public void followPath()
	{
		Action a = Actions.parallel(Actions.moveBy(300, 0, 5));
		
		Action b = Actions.parallel(Actions.moveBy(0, -200, 4));
		
		Action c = Actions.run(new Runnable() {
		
			public void run() {
				Mob.this.remove();
			}
		});
		
		this.addAction(Actions.sequence(a, b, c));
	}
	
}
