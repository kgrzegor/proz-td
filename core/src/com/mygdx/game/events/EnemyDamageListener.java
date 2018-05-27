package com.mygdx.game.events;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.mygdx.game.entities.enemies.Mob;

/**
 * Listener used by mob. If fired by DamageEvent cause damage to mob
 */
public class EnemyDamageListener implements EventListener
{

	private final Mob mob;

	public EnemyDamageListener(Mob mob)
	{
		this.mob = mob;
	}

	@Override
	public boolean handle(Event event)
	{
		if (!(event instanceof DamageEvent))
			return false;

		DamageEvent damageEvent = (DamageEvent) event;

		mob.takeDamage(damageEvent.getDamage());

		return true;
	}

}
