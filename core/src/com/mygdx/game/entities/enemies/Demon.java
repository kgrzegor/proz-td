package com.mygdx.game.entities.enemies;

import com.mygdx.game.controllers.MobController;

/**
 * Basic mob with medium speed and health
 */
public class Demon extends Mob
{
	public Demon(MobController mobController)
	{
		super(mobController, "mob/demon.png");

		this.gold = 50;
		this.health = 30;
		this.points = 100;
		this.speed = 100;

		this.followPath();
	}
}
