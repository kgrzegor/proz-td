package com.mygdx.game.entities;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Projectile extends AbstractEntity
{
	private final static int WIDHT = 10;
	private final static int HEIGHT = 10;

	float directionX;
	float directionY;
	private int damage;
	private double distance;

	ProjectileInterface projectileInterface;

	public Projectile(ProjectileInterface projectileInterface, int towerRadius, float towerX, float towerY,
			float targetX, float targetY, int damage)
	{
		super("projectile.png", (int) towerX, (int) towerY, WIDHT, HEIGHT);

		this.projectileInterface = projectileInterface;
		this.damage = damage;

		this.distance = Math.hypot(targetX - towerX, targetY - towerY);

		directionX = (float) Math.sin((targetX - towerX) / distance) * towerRadius;
		directionY = (float) Math.sin((targetY - towerY) / distance) * towerRadius;

	}

	public void fire(float projectileSpeed)
	{
		Action a = Actions.parallel(Actions.moveBy(directionX, directionY, (float) (distance / projectileSpeed)));
		Action c = Actions.run(new Runnable()
		{
			public void run()
			{
				projectileInterface.removeFromList(Projectile.this);
				Projectile.this.remove();
			}
		});

		this.addAction(Actions.sequence(a, c));

	}

	public void hit()
	{
		projectileInterface.removeFromList(this);
		this.remove();
	}

	public int getDamage()
	{
		return damage;
	}
}
