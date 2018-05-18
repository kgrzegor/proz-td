package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.events.EnemyDamageListener;

public class Mob extends Image 
{
	private final static int[] xCords = {145,470,470,1000,1000,670,670,1380};
	private final static int[] yCords = {455,455,180,180,400,400,580,580};
	private final static int WIDHT = 39;
	private final static int HEIGHT = 56;

	private final static int STARTING_X = 145;
	private final static int STARTING_Y = -100;
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
		
		this.addListener(new EnemyDamageListener(this));
	}

	public void followPath()
	{
		Action [] actions = new Action[xCords.length +1];
		for (int i = 0; i < actions.length - 1; ++i)
			actions[i] = Actions.moveTo(xCords[i], 720 - yCords[i], 3);
		
		
		actions[actions.length - 1] = Actions.run(new Runnable()
		{
			public void run()
			{
				mobInterface.removeFromStage(Mob.this);
				mobInterface.makeDamage();
			}
		});

		this.addAction(Actions.sequence(actions));
	}

	public void takeDamage(int damage)
	{
		health -= damage;
		
		if (health <= 0)
		{
			mobInterface.die(this);
			mobInterface.removeFromStage(this);
		}
		else
		{
			Action a = Actions.parallel(Actions.rotateBy(15, 0.1f), Actions.sizeBy(-4, -4, 0.1f));
			Action b = Actions.parallel(Actions.rotateBy(-15, 0.2f), Actions.sizeBy(4,4, 0.2f));
			this.addAction(Actions.sequence(a,b));
		}

	}

}
