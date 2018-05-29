package com.mygdx.game.entities.enemies;

import com.mygdx.game.controllers.MobController;
/**
 * Slow speed, tons of health, gold and points. Spawned only once at the end
 */
public class Boss extends Mob
{
	public Boss(MobController mobController)
	{
		super(mobController, "mob/boss.png",51,57);
		initStats();
		followPath();
	}

	@Override
	protected void initStats()
	{
		this.gold = 5000;
		this.health = 5000;
		this.points = 5000;
		this.speed = 50;
	}
}
