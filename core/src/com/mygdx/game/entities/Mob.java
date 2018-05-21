package com.mygdx.game.entities;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.events.EnemyDamageListener;

public class Mob extends Entity
{
	private final static int[] xCords = { 145, 470, 470, 1000, 1000, 670, 670, 1380 };
	private final static int[] yCords = { 455, 455, 180, 180, 400, 400, 580, 580 };
	private final static int WIDHT = 39;
	private final static int HEIGHT = 56;

	private final static int STARTING_X = 145;
	private final static int STARTING_Y = -100;

	private MobInterface mobInterface;
	private int health;
	private int currentPath;
	private int gold;
	private int points;

	public Mob(MobInterface mobInterface)
	{
		super("mob.png", STARTING_X, STARTING_Y, WIDHT, HEIGHT);
		this.mobInterface = mobInterface;

		this.health = 35;
		this.gold = 50;
		this.points = 10;
		this.currentPath = 0;
		this.addListener(new EnemyDamageListener(this));

	}

	public void followPath()
	{
		Action[] actions = new Action[(xCords.length - currentPath) + 1];
		int currentCords = currentPath;
		for (int i = 0; i < actions.length - 1; ++i, ++currentCords)
		{
			Action move = Actions.moveTo(xCords[currentCords], 720 - yCords[currentCords],
					caculateDistance(currentCords) / 100);
			Action pathNumber = Actions.run(new Runnable()
			{
				public void run()
				{
					Mob.this.currentPath++;
				}
			});
			actions[i] = Actions.sequence(move, pathNumber);

		}
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
		} else
		{
			Action a = Actions.parallel(Actions.rotateBy(15, 0.1f), Actions.sizeBy(-4, -4, 0.1f));
			Action b = Actions.parallel(Actions.rotateBy(-15, 0.2f), Actions.sizeBy(4, 4, 0.2f));
			this.addAction(Actions.sequence(a, b));
		}

	}

	public float caculateDistance(int id)
	{
		if (id == currentPath)
			return (float) Math.hypot(this.getX() - xCords[id], this.getY() - yCords[id]);
		else
			return (float) Math.hypot(xCords[id - 1] - xCords[id], yCords[id - 1] - yCords[id]);
	}

	public void freeze(float time)
	{
		this.clearActions();

		Timer.schedule(new Task()
		{
			public void run()
			{
				followPath();
			}
		}, time);
	}

	public int getGold()
	{
		return gold;
	}

	public void setGold(int gold)
	{
		this.gold = gold;
	}

	public int getPoints()
	{
		return points;
	}

}
