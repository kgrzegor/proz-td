package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.mygdx.game.entities.Projectile;

public class ProjectileController
{
	private float originX, originY;
	private ArrayList<Projectile> ProjectilesList;
	private int towerRadius;

	public ProjectileController(float X, float Y, int towerRadius)
	{
		this.originX = X;
		this.originY = Y;
		this.towerRadius = towerRadius;
		ProjectilesList = new ArrayList<Projectile>();
	}

	public void add(float projectileSpeed, int damage, float targetX, float targetY)
	{
		ProjectilesList.add(new Projectile(projectileSpeed, damage, towerRadius, 
										   originX, originY, targetX, targetY));
	}

}
