package com.mygdx.game.entities.enemies;

import com.mygdx.game.controllers.MobController;

/**
 * A little bit slow, but tough mob
 */
public class Mummy extends Mob
{
	public Mummy(MobController mobController)
	{
		super(mobController, "mob/mummy.png", 51, 57);
		initStats();
		followPath();
	}

	@Override
	protected void initStats()
	{
		this.gold = 100;
		this.health = 200;
		this.points = 350;
		this.speed = 85;
	}
}