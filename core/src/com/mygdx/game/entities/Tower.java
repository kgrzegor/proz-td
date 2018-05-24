package com.mygdx.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.controllers.ProjectileController;
import com.mygdx.game.controllers.UpgradeController;
import com.mygdx.game.services.GoldService;

public class Tower extends AbstractEntity
{
	public final static int WIDHT = 74;
	public final static int HEIGHT = 120;

	private float X;
	private float Y;

	private ProjectileController projectileController;
	private float projectileSpeed;
	private float fireRateCooldown; // smaller = better
	private int damage;
	private int range;
	private boolean shooting;
	private ArrayList<Mob> targets;
	private Stage stage;
	private UpgradeController towerController;
	private GoldService goldService;

	public Tower(int xCord, int yCord, Stage stage, ArrayList<Mob> mobsList, GoldService goldService)
	{
		super("tower.png", xCord, yCord, WIDHT, HEIGHT);

		this.stage = stage;
		this.targets = mobsList;
		this.goldService = goldService;
		this.setPosition(15 + xCord, yCord + 7);
		init();

		this.startShooting();
	}

	public void init()
	{
		this.X = this.getX(Align.center);
		this.Y = this.getY(Align.center);
		this.range = 200;
		this.projectileController = new ProjectileController(X, Y, range, stage, targets);
		this.projectileSpeed = 300f;
		this.fireRateCooldown = 1.5f;
		this.damage = 10;
		this.towerController = new UpgradeController(this, stage, goldService);

		this.addListener(new ClickListener()
		{
			public void clicked(InputEvent event, float x, float y)
			{
				towerController.showMenu();
			}
		});

	}

	private void startShooting()
	{
		float fireRate;
		if (shooting)
			fireRate = fireRateCooldown;
		else
			fireRate = 0.05f;

		Timer.schedule(new Task()
		{
			public void run()
			{
				shooting = shoot();
				startShooting();
			}

		}, fireRate);

	}

	private boolean shoot()
	{
		float targetX;
		float targetY;
		for (Mob target : targets)
		{
			targetX = target.getX(Align.center);
			targetY = target.getY(Align.center);

			if (Math.hypot(targetX - X, targetY - Y) <= range)
			{
				projectileController.add(projectileSpeed, damage, targetX, targetY);
				return true;
			}
		}
		return false;
	}

	public ProjectileController getProjectileController()
	{
		return projectileController;
	}

	public void biggerRange()
	{
		range += 50;
		projectileSpeed += 100;
		projectileController.setRange(range);
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
		System.out.println("My damage: " + damage);
		Timer.schedule(new Task()
		{
			public void run()
			{
				damage /= 1 + percent / 100;
				System.out.println("My damage: " + damage);
			}

		}, 15);
	}

	public int getTowerY()
	{
		return (int) Y;
	}

	public int getTowerX()
	{
		return (int) X;
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
}
