package com.mygdx.game.controllers;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.entities.Projectile;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.entities.enemies.Mob;

/**
 * Every tower have its own projectilleController, creates all projectiles, aim
 * them and tells how it should move. Checks if projectile hit anyone.
 */
public class ProjectileController
{
	private int myX, myY;

	private LinkedList<Projectile> ProjectilesList;
	private Stage stage;
	private List<Mob> targets;
	private Tower tower;

	public ProjectileController(Tower tower, Stage stage, List<Mob> targets)
	{
		this.stage = stage;
		this.targets = targets;
		this.tower = tower;

		this.myX = tower.getTowerX();
		this.myY = tower.getTowerY();

		ProjectilesList = new LinkedList<Projectile>();
	}

	/**
	 * Iterates over every mob in game and checks if can shoot them
	 * 
	 * @return True if found target, otherwise false
	 */
	public boolean findTarget()
	{
		float targetX;
		float targetY;

		for (Mob target : targets)
		{
			targetX = target.getX(Align.center);
			targetY = target.getY(Align.center);

			if (Math.hypot(targetX - myX, targetY - myY) <= tower.getRange())
			{
				shoot(targetX, targetY);
				return true;
			}
		}
		return false;
	}

	/**
	 * Calculates projectile's path and add it to stage. Projectile is removed when
	 * traveled tower range.
	 * 
	 * @param projectileSpeed
	 *            Defines px traveled every second.
	 * @param damage
	 *            Defines damage done to whatever it hit.
	 */
	public void shoot(float targetX, float targetY)
	{
		final Projectile newProjectile = new Projectile(myX, myY, tower.getDamage());

		double distance = Math.hypot(targetX - myX, targetY - myY); // distance between target and tower
		float time = (float)(tower.getRange() / tower.getProjectileSpeed());
		
		float directionX = (float) Math.sin((targetX - myX) / distance) * tower.getRange();
		float directionY = (float) Math.sin((targetY - myY) / distance) * tower.getRange();

		Action move = Actions.moveBy(directionX, directionY, time); //move in direction of target
		
		Action outOfRange = Actions.run(new Runnable() //remove when out of tower range
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
	 * Iterates over every projectile and mob checking if anything is hit.
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
					m.takeDamage(p.getDamage());
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
}
