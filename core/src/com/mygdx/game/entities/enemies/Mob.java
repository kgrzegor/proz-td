package com.mygdx.game.entities.enemies;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.controllers.MobController;
import com.mygdx.game.entities.AbstractEntity;
import com.mygdx.game.entities.PowerupAffected;
import com.mygdx.game.events.PowerupListener;

/**
 * Entity that follows map path. Projectile controller aims for it. Can be
 * damaged when hit by projectile. Removed from stage when have no health
 */
public abstract class Mob extends AbstractEntity implements PowerupAffected
{
	/**
	 * Cords (x,y) are points where mob turn either left or right. Starting x and y
	 * is point where mob is placed when created
	 */
	private final static int[] xCords = { 145, 470, 470, 1000, 1000, 670, 670, 1380 };
	private final static int[] yCords = { 265, 265, 540, 540, 320, 320, 140, 140 };

	private final static int STARTING_X = 145;
	private final static int STARTING_Y = -100;

	private MobController mobController;
	private int currentPath;

	protected int health;
	protected int gold;
	protected int points;
	protected int speed;

	public Mob(MobController mobController, String name, int widht, int height)
	{
		super(name, STARTING_X, STARTING_Y, widht, height);
		this.mobController = mobController;
		this.currentPath = 0;

		initStats();
		initPowerupListener();
	}

	abstract void initStats();

	public void initPowerupListener()
	{
		this.addListener(new PowerupListener(this));
	}

	/**
	 * Uses cords to define path for mob
	 */
	public void followPath()
	{
		Action[] actions = new Action[(xCords.length - currentPath) + 1]; // Actions length == paths left + making
																			// damage when traveled entire path
		int currentCords = currentPath;

		for (int i = 0; i < actions.length - 1; ++i, ++currentCords)
		{
			float time = caculateDistance(currentCords) / speed;
			Action move = Actions.moveTo(xCords[currentCords], yCords[currentCords], time); // move to next turn

			Action pathNumber = Actions.run(new Runnable() // when turning increment current path
			{
				public void run()
				{
					Mob.this.currentPath++;
				}
			});
			actions[i] = Actions.sequence(move, pathNumber);
		}

		actions[actions.length - 1] = Actions.run(new Runnable() // make damage when traveled entire path
		{
			public void run()
			{
				mobController.damagePlayer(Mob.this);
			}
		});

		this.addAction(Actions.sequence(actions));
	}

	/**
	 * Taking damage from projectile with small animation, dying when no health
	 * left.
	 */
	public void takeDamage(int damage)
	{
		health -= damage;

		if (health <= 0)
		{
			mobController.mobDied(this);
		} else
		{
			// rotate ccw and shrink a little bit
			Action a = Actions.parallel(Actions.rotateBy(15, 0.1f), Actions.sizeBy(-4, -4, 0.1f));

			// go back to normal shape and size
			Action b = Actions.parallel(Actions.rotateBy(-15, 0.2f), Actions.sizeBy(4, 4, 0.2f));

			this.addAction(Actions.sequence(a, b));
		}

	}

	/**
	 * Calculate distance between current place and next turn or previous and next
	 * turn based on position of mob.
	 * 
	 * @param id
	 *            Next turn cords
	 * @return Distance in px
	 */
	public float caculateDistance(int id)
	{
		if (id == currentPath)
			return (float) Math.hypot(this.getX() - xCords[id], this.getY() - yCords[id]);
		else
			return (float) Math.hypot(xCords[id - 1] - xCords[id], yCords[id - 1] - yCords[id]);
	}

	/**
	 * Stay in place for given time and start following path once again.
	 */
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

	/**
	 * GETTERS
	 */
	public int getGold()
	{
		return gold;
	}

	public int getPoints()
	{
		return points;
	}

}
