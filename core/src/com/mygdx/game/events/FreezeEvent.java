package com.mygdx.game.events;

import com.badlogic.gdx.scenes.scene2d.Event;
/**
 * Used when freezing mobs
 */
public class FreezeEvent extends Event
{
	private final float time;

	public FreezeEvent(float time)
	{
		this.time = time;
	}

	public float getTime()
	{
		return time;
	}
}
