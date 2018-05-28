package com.mygdx.game.entities.enemies;

import com.mygdx.game.controllers.MobController;

/**
 * Weak and slow mob, spawned in large quantity
 */
public class Snail extends Mob
{
	public Snail(MobController mobController)
	{
		super(mobController, "mob/snail.png", 54, 45);
		initStats();
		followPath();
	}

	@Override
	protected void initStats()
	{
		this.gold = 10;
		this.health = 15;
		this.points = 20;
		this.speed = 50;
	}
}
