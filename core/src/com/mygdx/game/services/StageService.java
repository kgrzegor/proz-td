package com.mygdx.game.services;


public class StageService
{
	private int currentStage;
	private int lastStage;
	public StageService()
	{
		this.currentStage = 0;
		this.lastStage = 2;
	}

	public void nextStage()
	{
		if (currentStage < lastStage)
			++currentStage;
	}

	public int getCurrentStage()
	{
		return currentStage;
	}

	public int getLastStage()
	{
		return lastStage;
	}

	public boolean hasNextStage()
	{
		return !(currentStage == lastStage);
	}
}
