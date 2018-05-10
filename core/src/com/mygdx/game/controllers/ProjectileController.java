package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.entities.Projectile;

public class ProjectileController
{
	private float towerX, towerY;
	private ArrayList<Projectile> ProjectilesList;
	private Projectile newProjectile;
	private int towerRadius;
	private Stage stage;

	public ProjectileController(float X, float Y, int towerRadius, Stage stage)
	{
		this.towerX = X;
		this.towerY = Y;
		this.towerRadius = towerRadius;
		ProjectilesList = new ArrayList<Projectile>();
		this.stage = stage;
	}

	public void add(float projectileSpeed, int damage, float targetX, float targetY)
	{
		newProjectile = new Projectile(towerRadius, towerX, towerY, targetX, targetY);
	
		stage.addActor(newProjectile);
		newProjectile.fire(projectileSpeed);
		ProjectilesList.add(newProjectile);
	}
	// check if any Projectile hit target
}
