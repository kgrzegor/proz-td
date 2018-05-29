package com.mygdx.game.events;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.entities.PowerupAffected;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.entities.enemies.Mob;

public class PowerupListener implements EventListener
{
	private final PowerupAffected affected;

	public PowerupListener(PowerupAffected affected)
	{
		this.affected = affected;
	}

	@Override
	public boolean handle(Event event)
	{
		if (event instanceof FreezeEvent && affected instanceof Mob)
		{
			FreezeEvent freezeEvent = (FreezeEvent) event;
			Mob mob = (Mob) affected;
			mob.freeze(freezeEvent.getTime());
			return true;
		}

		if (event instanceof DamageEvent && affected instanceof Tower)
		{
			DamageEvent damageEvent = (DamageEvent) event;
			Tower tower = (Tower) affected;
			tower.bonusDamage(damageEvent.getDamage());
			return true;
		}

		if (event instanceof DamageEvent && affected instanceof Mob)
		{
			DamageEvent damageEvent = (DamageEvent) event;
			Mob mob = (Mob) affected;
			mob.takeDamage(damageEvent.getDamage());
			return true;
		}

		return false;
	}
}