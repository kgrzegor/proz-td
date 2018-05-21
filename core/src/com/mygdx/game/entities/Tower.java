package com.mygdx.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.controllers.ProjectileController;
import com.mygdx.game.controllers.TowerController;

public class Tower extends Image
{
	private final static int WIDHT = 74;
	private final static int HEIGHT = 120;

	private float myX;
	private float myY;

	private ProjectileController projectileController;
	private float projectileSpeed;
	private float fireRateCooldown; // smaller = better
	private int damage;
	private int towerRadius;
	private ArrayList<Mob> targets;
	private Stage stage;
	private TowerController towerController;
	public Tower(int xCord, int yCord, Stage stage, ArrayList<Mob> mobsList)
	{
		super(new Texture("tower.png"));
		this.stage = stage;
		this.targets = mobsList;
		this.setPosition(15 + xCord, yCord + 7);
		init();

		this.startShooting();
	}

	public void init()
	{
		this.setOrigin(WIDHT / 2, HEIGHT / 2);
		this.setSize(WIDHT, HEIGHT);
		this.myX = this.getX(Align.center);
		this.myY = this.getY(Align.center);
		this.towerRadius = 500;
		this.projectileController = new ProjectileController(this.myX, this.myY, towerRadius, stage, targets);
		this.projectileSpeed = 150f;
		this.fireRateCooldown = 0.8f;
		this.damage = 10;
		this.towerController = new TowerController(this, stage, myX, myY);

		this.addListener(new ClickListener()
			{
				public void clicked(InputEvent event, float x, float y)
				{
					towerController.showMenu();
				}
			}
		);
		
	}

	private void startShooting()
	{
		Timer.schedule(new Task()
		{
			public void run()
			{
				shoot();
				startShooting();
			}

		}, fireRateCooldown);
		
	}

	private void shoot()
	{
		float targetX;
		float targetY;
		for (Mob target : targets)
		{

			targetX = target.getX(Align.center);
			targetY = target.getY(Align.center);

			if (Math.hypot(targetX - myX, targetY - myY) <= towerRadius)
			{
				projectileController.add(projectileSpeed, damage, targetX, targetY);
				break;
			}

		}
	}

	public ProjectileController getProjectileController()
	{
		return projectileController;
	}

	public void upgradeRadius()
	{
		towerRadius *= 1.1;
		projectileController.setTowerRadius(towerRadius);
		System.out.println("radius = " + towerRadius);
	}

	public void upgradeDamage()
	{
		damage *= 1.5;		
	}

	public void upgradeFireRateCooldown()
	{
		
		fireRateCooldown *= 0.8;
		//TODO this one is bugged;
	}
	
	public void bonusDamage(final float percent)
	{
		damage *= 1 + percent/100;
		System.out.println("My damage: " + damage);
		Timer.schedule(new Task()
		{
			public void run()
			{
				damage/= 1 + percent/100;
				System.out.println("My damage: " + damage);
			}

		}, 15);
	}
}
