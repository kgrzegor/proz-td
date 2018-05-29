package com.mygdx.game.entities;

import com.badlogic.gdx.scenes.scene2d.Event;

/**
 * Implemented when entity is affected by powerup effects
 */
public interface PowerupAffected
{
	public void initPowerupListener();
	
	public boolean fire(Event event);
}