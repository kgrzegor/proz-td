package com.mygdx.game.entities.enemies;

import com.mygdx.game.controllers.MobController;

public class Yeti extends Mob
{
	public Yeti(MobController mobController)
	{
		super(mobController, "yeti.png");

		this.gold = 50;
		this.health = 30;
		this.points = 100;
		this.speed = 70;
	}

	@Override
	public void freeze(float time)
	{
		this.clearActions();
		this.speed *= 1.5;
		followPath();
	}
}
