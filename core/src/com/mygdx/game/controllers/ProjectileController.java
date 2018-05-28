package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.entities.Projectile;
import com.mygdx.game.entities.enemies.Mob;

/**
 * Every tower have its own projectilleController, creates all
 * projectiles, aim them and tells how it should move. Can check if projectile
 * hit anyone.
 */
public class ProjectileController
{
	private float towerX, towerY;
	private ArrayList<Projectile> ProjectilesList;
	private int range;
	private Stage stage;
	private ArrayList<Mob> targets;

	public ProjectileController(float X, float Y, int range, Stage stage, ArrayList<Mob> targets)
	{
		this.towerX = X;
		this.towerY = Y;
		this.range = range;
		this.stage = stage;
		this.targets = targets;
		ProjectilesList = new ArrayList<Projectile>();
	}

	/**
	 * Calculates projectile's path and add it to stage. Projetile is removed when
	 * traveled tower range.
	 * 
	 * @param projectileSpeed
	 *            Defines px traveled every second.
	 * @param damage
	 *            Defines damage done to whatever it hit.
	 */
	public void add(float projectileSpeed, int damage, float targetX, float targetY)
	{
		final Projectile newProjectile = new Projectile(towerX, towerY, damage);

		double distance = Math.hypot(targetX - towerX, targetY - towerY);
		float directionX = (float) Math.sin((targetX - towerX) / distance) * range;
		float directionY = (float) Math.sin((targetY - towerY) / distance) * range;

		Action move = Actions.moveBy(directionX, directionY, (float) (distance / projectileSpeed));
		Action outOfRange = Actions.run(new Runnable()
		{
			public void run()
			{
				removeProjectile(newProjectile);
			}
		});

		stage.addActor(newProjectile);
		newProjectile.addAction(Actions.sequence(move, outOfRange));

		ProjectilesList.add(newProjectile);
	}

	/**
	 * Iterates over every projectile and mobs checking if anything is hit.
	 * Something is hit when distance between center of projectile and target is
	 * less than half height or half width of target
	 */
	public void checkHits()
	{
		for (Projectile p : ProjectilesList)
			for (Mob m : targets)
			{
				if ((Math.abs(m.getX(Align.center) - p.getX(Align.center))) <= m.getWidth() / 2
						&& (Math.abs(m.getY(Align.center) - p.getY(Align.center))) <= m.getHeight() / 2)
				{
					m.fire(p.getDamageEvent());
					removeProjectile(p);
					return;
				}
			}
	}

	private void removeProjectile(Projectile p)
	{
		ProjectilesList.remove(p);
		p.remove(); // from stage
	}

	/**
	 * Used when tower have range upgraded
	 */
	public void setRange(int range)
	{
		if (range >= this.range)
			this.range = range;
		else
			throw new IllegalArgumentException("New range can't be less than previous");
	}
}
