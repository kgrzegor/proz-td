package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.entities.Projectile;
import com.mygdx.game.entities.enemies.Mob;

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

	public void add(float projectileSpeed, int damage, float targetX, float targetY)
	{
		final Projectile newProjectile = new Projectile(towerX, towerY, damage);
		double distance = Math.hypot(targetX - towerX, targetY - towerY);
		float directionX = (float) Math.sin((targetX - towerX) / distance) * range;
		float directionY = (float) Math.sin((targetY - towerY) / distance) * range;

		Action move = Actions.parallel(Actions.moveBy(directionX, directionY, (float) (distance / projectileSpeed)));
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

	public void setRange(int range)
	{
		this.range = range;
	}
}
