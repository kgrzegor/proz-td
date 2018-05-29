package com.mygdx.game.entities.enemies;

import com.mygdx.game.controllers.MobController;

/**
 * Mob with different freeze effect, slow but have more health
 */
public class Yeti extends Mob
{

	public Yeti(MobController mobController)
	{
		super(mobController, "mob/yeti.png", 50, 51);
		this.followPath();
	}

	public Yeti(MobController mobController, String subtype, int bonus)
	{
		super(mobController, "mob/" + subtype + "yeti.png", 56, 57);
		bonusStats(bonus);
		this.followPath();
	}

	@Override
	protected void initStats()
	{
		this.gold = 75;
		this.health = 30;
		this.points = 200;
		this.speed = 70;
	}

	private void bonusStats(int bonus)
	{

		this.gold += bonus * 5;
		this.health += bonus * 3;
	}

	@Override
	public void freeze(float time)
	{
		this.clearActions();
		this.speed *= 1.5;
		followPath();
	}

}
