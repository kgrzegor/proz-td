package com.mygdx.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.controllers.ProjectileController;

public class Tower extends Image
{
	private final static int WIDHT = 74;
	private final static int HEIGHT = 120;

	private float myX;
	private float myY;

	private ProjectileController projectileController;
	private float projectileSpeed;
	@SuppressWarnings("unused")
	private float fireRate; // smaller = better
	private int damage;
	private int towerRadius;
	private ArrayList<Mob> targets;
	private Stage stage;

	public Tower(int xCord, int yCord, Stage stage, ArrayList<Mob> mobsList)
	{
		super(new Texture("tower.png"));
		this.stage = stage;
		this.targets = mobsList;
		this.setPosition((100 - 72) / 2 + xCord, yCord);  // magic numbers
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
		this.projectileController = new ProjectileController(this.myX, this.myY, towerRadius, stage);
		this.projectileSpeed = 1.5f;
		this.fireRate = 3;
		this.damage = 10;
		
	}

	private void startShooting()
	{
		float targetX;
		float targetY;
		for (Mob target : targets)
		{

			targetX = target.getX(Align.center);
			targetY = target.getY(Align.center);

			if (Math.hypot(targetX - myX, targetY - myY) <= towerRadius)
				this.shoot(targetX, targetY);
		}

	}

	private void shoot(float targetX, float targetY)
	{
		projectileController.add(projectileSpeed, damage, targetX, targetY);
	}

}
