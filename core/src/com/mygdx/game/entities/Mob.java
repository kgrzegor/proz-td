package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Mob extends Image
{
	private final static int WIDHT = 78;
	private final static int HEIGHT = 112;
	
	private final static int STARTING_X = -100; //TODO this should be current 
	private final static int STARTING_Y = 300;  // stage base (X,Y)
	

	public Mob()
	{
		super(new Texture("mob.png"));
		init();		
		//on click show mob info
	}
	
	private void init()
	{
		
		this.setOrigin(WIDHT / 2, HEIGHT / 2);
		this.setSize(WIDHT, HEIGHT);
		this.setPosition(STARTING_X, STARTING_Y);		
	}

	public void followPath()
	{
		Action a = Actions.parallel(Actions.moveBy(1400, 0, 15));
		
		//Action b = Actions.parallel(Actions.moveBy(0, -200, 4));
		
		Action c = Actions.run(new Runnable() {
		
			public void run() {
				Mob.this.remove();
				}
		});
		
		this.addAction(Actions.sequence(a, c));
	}
	
}
