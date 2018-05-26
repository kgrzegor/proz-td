package com.mygdx.game.services;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class TimeService
{

	private int time;
	private boolean stopped;

	public TimeService()
	{
		this.stopped = false;
		resetTime();
	}

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
		time = 5;
	}

	public int getTime()
	{
		return time;
	}

	public void setStopped(boolean stopped)
	{
		this.stopped = stopped;
	}

}
