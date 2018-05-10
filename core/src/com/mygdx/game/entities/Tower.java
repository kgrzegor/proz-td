package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.controllers.ProjectileController;

public class Tower extends Image
{
	private final static int WIDHT = 74;
	private final static int HEIGHT = 120;
	
	private ProjectileController projectileController;
	private float projectileSpeed;
	private float fireRate; //smaller = better
	private int damage;
	private int towerRadius;
	
	public Tower(int xCord, int yCord)
	{
		super(new Texture("tower.png"));
		init();
		
		this.setPosition((100 - 72) / 2 + xCord, yCord); // magic numbers
		//start shooting
	}

	public void init()
	{
		this.setOrigin(WIDHT / 2, HEIGHT / 2);
		this.setSize(WIDHT, HEIGHT);
		this.towerRadius = 50;
		this.projectileController = new ProjectileController(this.getOriginX(), this.getOriginY(),towerRadius);
		this.projectileSpeed = 1;
		this.fireRate = 3;
		this.damage = 10;
		
		
	}

	
	private void shoot(float targetX, float targetY)
	{
		projectileController.add(projectileSpeed, damage, targetX, targetY);
	}

	
}

