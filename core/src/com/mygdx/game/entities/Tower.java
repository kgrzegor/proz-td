package com.mygdx.game.entities;

import java.util.LinkedList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.controllers.ProjectileController;
import com.mygdx.game.controllers.UpgradeController;
import com.mygdx.game.entities.enemies.Mob;
import com.mygdx.game.events.PowerupListener;
import com.mygdx.game.services.GoldService;

/**
 * Tower is built by tower controller. It have its own upgrade and projectile
 * controller.
 */
public class Tower extends AbstractEntity implements PowerupAffected
{
	public final static int WIDHT = 74;
	public final static int HEIGHT = 120;

	private double projectileSpeed;
	private float fireRateCooldown;// smaller = better
	private int damage;
	private int range;
	private boolean shooting;

	private ProjectileController projectileController;
	private UpgradeController upgradeController;

	public Tower(int xCord, int yCord, Stage stage, LinkedList<Mob> mobsList, GoldService goldService)
	{
		super("map/tower.png", xCord, yCord, WIDHT, HEIGHT);
		this.setPosition(15 + xCord, yCord + 7);

		this.projectileController = new ProjectileController(this, stage, mobsList);
		this.upgradeController = new UpgradeController(this, stage, goldService);

		init();

		this.startShooting();
	}

	public void init()
	{
		this.range = 200;
		this.projectileSpeed = 500f;
		this.fireRateCooldown = 1.5f;
		this.damage = 10;

		this.addListener(new ClickListener()
		{
			public void clicked(InputEvent event, float x, float y)
			{
				upgradeController.showMenu();
			}
		});
	}

	/**
	 * Recursively calls itself every 0.05 s to find target. When target have been
	 * found calls itself after fireRateCooldown seconds.
	 */
	private void startShooting()
	{
		float fireRate;
		if (shooting)
			fireRate = fireRateCooldown;
		else
			fireRate = 0.05f; // check after 0.05 if target is available

		Timer.schedule(new Task()
		{
			public void run()
			{
				shooting = projectileController.findTarget();
				startShooting();
			}
		}, fireRate);
	}

	/**
	 * When range is upgraded projectile often missed target on larger distance,
	 * hence projectileSpeed needs to be upgraded
	 */
	public void biggerRange()
	{
		range += 50;
		projectileSpeed += 100;
	}

	public void addDamage()
	{
		damage *= 1.5;
	}

	public void lowerFireRateCooldown()
	{
		fireRateCooldown *= 0.9;
	}

	public void bonusDamage(final float percent)
	{
		damage *= 1 + percent / 100;
		Timer.schedule(new Task()
		{
			public void run()
			{
				damage /= 1 + percent / 100;
			}

		}, 15);
	}

	/*
	 * GETTERS
	 */
	public int getTowerY()
	{
		return (int) this.getY(Align.center);
	}

	public int getTowerX()
	{
		return (int) this.getX(Align.center);
	}

	public int getRange()
	{
		return range;
	}

	public int getDamage()
	{
		return damage;
	}

	public float getFireRateCooldown()
	{
		return fireRateCooldown;
	}

	public double getProjectileSpeed()
	{
		return projectileSpeed;
	}

	public ProjectileController getProjectileController()
	{
		return projectileController;
	}

	public void initPowerupListener()
	{
		this.addListener(new PowerupListener(this));
	}
}
