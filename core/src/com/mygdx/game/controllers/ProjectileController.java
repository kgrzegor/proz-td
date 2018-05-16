package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.entities.Mob;
import com.mygdx.game.entities.Projectile;
import com.mygdx.game.entities.ProjectileInterface;
import com.mygdx.game.events.DamageEvent;

public class ProjectileController
{
	private float towerX, towerY;
	private ArrayList<Projectile> ProjectilesList;
	private Projectile newProjectile;
	private int towerRadius;
	private Stage stage;
	private ArrayList<Mob> targets;

	public ProjectileController(float X, float Y, int towerRadius, Stage stage, ArrayList<Mob> targets)
	{
		this.towerX = X;
		this.towerY = Y;
		this.towerRadius = towerRadius;
		ProjectilesList = new ArrayList<Projectile>();
		this.stage = stage;
		this.targets = targets;
		
	}

	public void add(float projectileSpeed, int damage, float targetX, float targetY)
	{
		newProjectile = new Projectile(new ProjectileInterface()
		{

			@Override
			public void removeFromList(Projectile projectile)
			{
				ProjectilesList.remove(projectile);
			}
		}, towerRadius, towerX, towerY, targetX, targetY, damage);

		stage.addActor(newProjectile);
		newProjectile.fire(projectileSpeed);
		ProjectilesList.add(newProjectile);
	}

	public void checkHits()
	{
		for (Projectile p : ProjectilesList)
			for (Mob m : targets)
			{
				if ((Math.abs(m.getX(Align.center) - p.getX(Align.center))) <= m.getWidth() / 2
						&& (Math.abs(m.getY(Align.center) - p.getY(Align.center))) <= m.getHeight() / 2)
				{
					m.fire(new DamageEvent(p.getDamage()));
					p.hit();
					return;
				}
			}
	}
}
