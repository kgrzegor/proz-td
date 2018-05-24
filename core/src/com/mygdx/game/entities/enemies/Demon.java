package com.mygdx.game.entities.enemies;

import com.mygdx.game.controllers.MobController;

public class Demon extends Mob
{
	public Demon(MobController mobController)
	{
		super(mobController, "demon.png");

		this.gold = 50;
		this.health = 30;
		this.points = 100;
		this.speed = 100;
	}
}