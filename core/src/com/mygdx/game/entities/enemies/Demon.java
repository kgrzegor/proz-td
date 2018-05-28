package com.mygdx.game.entities.enemies;

import com.mygdx.game.controllers.MobController;

/**
 * Basic mob with medium speed and health
 */
public class Demon extends Mob
{
	private final static int WIDHT = 39;
	private final static int HEIGHT = 56;
	
	
	public Demon(MobController mobController)
	{
		super(mobController, "mob/demon.png",WIDHT,HEIGHT);
		initStats();
		followPath();
	}

	public Demon(MobController mobController, String subtype, int bonus)
	{
		super(mobController, "mob/" + subtype + "demon.png",WIDHT,HEIGHT);
		bonusStats(bonus);
		followPath();
	}

	private void bonusStats(int bonus)
	{
		this.gold += bonus;
		this.points += bonus * 2;
		this.speed += bonus * 2;
	}

	@Override
	protected void initStats()
	{
		this.gold = 30;
		this.health = 30;
		this.points = 100;
		this.speed = 100;
	}
}
