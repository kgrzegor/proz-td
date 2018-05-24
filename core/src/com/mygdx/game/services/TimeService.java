package com.mygdx.game.services;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class TimeService
{

	private int time;
	StageService stageService;

	public TimeService(StageService stageService)
	{
		this.time = 5;
		this.stageService = stageService;
	}

	public void start()
	{
		Timer.schedule(new Task()
		{

			@Override
			public void run()
			{
				if (stageService.getCurrentStage() != stageService.getLastStage())
				{
					start();
					nextSecond();
				}

				else
				{
					time = 0;
				}

			}
		}, 1);
	}

	protected void nextSecond()
	{
		if (time > 0)
			--time;
		else
		{
			stageService.nextStage();
			resetTime();
		}
	}

	public void resetTime()
	{
		time = 5;
	}

	public int getTime()
	{
		return time;
	}

}
