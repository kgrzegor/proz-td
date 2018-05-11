package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Mob extends Image
{
	private final static int WIDHT = 39;
	private final static int HEIGHT = 56;

	private final static int STARTING_X = -100; // TODO this should be current stage base (X,Y)
	private final static int STARTING_Y = 300;
	MobInterface mobInterface;
	int health;
	
	public Mob(MobInterface mobInterface)
	{
		super(new Texture("mob.png"));
		this.mobInterface = mobInterface;
		init();
		// TODO on click show mob info
	}

	private void init()
	{

		this.setOrigin(WIDHT / 2, HEIGHT / 2);
		this.setSize(WIDHT, HEIGHT);
		this.setPosition(STARTING_X, STARTING_Y);
		this.health = 35;
	}

	public void followPath()
	{
		Action a = Actions.parallel(Actions.moveBy(1400, 0, 15));

		// Action b = Actions.parallel(Actions.moveBy(0, -200, 4));

		Action c = Actions.run(new Runnable()
		{
			public void run()
			{
				mobInterface.removeFromStage(Mob.this);
				mobInterface.makeDamage();
			}
		});

		this.addAction(Actions.sequence(a, c));
	}

	public void takeDamage(int damage)
	{
		health -=damage;
		if (health <= 0)
		{
			mobInterface.die(this);
			mobInterface.removeFromStage(this);
		}
		
	}

}
