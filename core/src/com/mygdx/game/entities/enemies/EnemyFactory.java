package com.mygdx.game.entities.enemies;

import com.mygdx.game.controllers.MobController;

/**
 * Creates every mob used in game.
 */
public class EnemyFactory
{
	MobController mobController;

	public EnemyFactory(MobController mobController)
	{
		this.mobController = mobController;
	}

	public Mob createMob(MobType type)
	{
		switch (type)
		{
		case Yeti:
			return new Yeti(mobController);
		case BiggerYeti:
			return new Yeti(mobController, "bigger", 30);
		case Demon:
			return new Demon(mobController);
		case RedDemon:
			return new Demon(mobController, "red", 25);
		case Snail:
			return new Snail(mobController);
		case Mummy:
			return new Mummy(mobController);
		case Boss:
			return new Boss(mobController);
		default:
			throw new IllegalArgumentException("Wrong mob type");
		}
	}
}
