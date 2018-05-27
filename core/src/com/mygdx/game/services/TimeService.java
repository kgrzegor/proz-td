package com.mygdx.game.services;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

/**
 * Provides starting, restarting and stopping stage timer
 */
public class TimeService
{

	private int time;
	private boolean stopped;

	public TimeService()
	{
		this.stopped = false;
		resetTime();
	}

	/**
	 * Recursively called every one second until timer is stopped
	 */
	public void start()
	{
		Timer.schedule(new Task()
		{
			@Override
			public void run()
			{
				nextSecond();
				if (!stopped)
					start();
				else
					resetTime();
			}
		}, 1);
	}

	protected void nextSecond()
	{
		if (time > 0)
			--time;
	}

	public void resetTime()
	{
		time = 60;
	}

	public int getTime()
	{
		return time;
	}

	/**
	 * Once stopped needs to be changed to false and start() called again
	 */
	public void setStopped(boolean stopped)
	{
		this.stopped = stopped;
	}

}
