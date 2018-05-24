package com.mygdx.game.services;

import com.mygdx.game.controllers.MobController;

public class StageService
{
	private int currentStage;
	private int lastStage;
	private MobController mobController;

	public StageService(MobController mobController)
	{
		this.currentStage = 0;
		this.lastStage = 4;
		this.mobController = mobController;
	}

	public void nextStage()
	{
		if (currentStage < lastStage)
		{
			++currentStage;
			mobController.startWave(currentStage);
		}

	}

	public int getCurrentStage()
	{
		return currentStage;
	}

	public int getLastStage()
	{
		return lastStage;
	}

}
