package com.mygdx.game.entities;

import com.mygdx.game.events.DamageEvent;

public class Projectile extends AbstractEntity
{
	private final static int WIDHT = 10;
	private final static int HEIGHT = 10;

	private DamageEvent damageEvent;

	public Projectile(float towerX, float towerY,int damage)
	{
		super("projectile.png", (int) towerX, (int) towerY, WIDHT, HEIGHT);
		this.damageEvent = new DamageEvent(damage);
	}

	public DamageEvent getDamageEvent()
	{
		return damageEvent;
	}
}
