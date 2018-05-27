package com.mygdx.game.entities.enemies;

import com.mygdx.game.controllers.MobController;
/**
 * Creates every mob in game.
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
		case Demon:
			return new Demon(mobController);
		default:
			return null;
		}
	}
}
