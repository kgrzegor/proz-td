package com.mygdx.game.entities;


/**
 * Entity with projectile texture, create by ProjectileController. When someone
 * is hit, damage event is used.
 */
public class Projectile extends AbstractEntity
{
	private final static int WIDHT = 10;
	private final static int HEIGHT = 10;

	private int damage;

	public Projectile(float towerX, float towerY, int damage)
	{
		super("map/projectile.png", (int) towerX, (int) towerY, WIDHT, HEIGHT);
		this.damage = damage;
	}

	public int getDamage()
	{
		return damage;
	}
}
