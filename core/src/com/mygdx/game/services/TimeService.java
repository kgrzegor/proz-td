package com.mygdx.game.services;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class TimeService
{

	int time;

	public TimeService()
	{
		this.time = 0;
	}

	public void start()
	{
		Timer.schedule(new Task()
		{

			@Override
			public void run()
			{
				nextSecond();
			}
		}, 1, 1);
	}

	protected void nextSecond()
	{
		if (time > 0)
		--time;
		else
		{
			waveService.nextWave();
			resetTime();
		}
			
		
	}

	public void resetTime()
	{
		time = 0;
	}
}
