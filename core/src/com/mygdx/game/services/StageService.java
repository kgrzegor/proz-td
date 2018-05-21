package com.mygdx.game.services;

public class StageService
{
	int currentStage;
	int lastStage;

	public StageService()
	{
		this.currentStage = 0;
		this.lastStage = 10;
	}
	
	public void nextStage()
	{
		if (currentStage < lastStage)
			++currentStage;
	}
}
