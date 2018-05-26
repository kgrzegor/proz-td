package com.mygdx.game.services;

public class StageService
{
	private int currentStage;
	private int lastStage;
	private final float spawnTime;
	private final int[] spawnCount = { 4, 1 };

	public StageService()
	{
		this.currentStage = 0;
		this.lastStage = 2;
		this.spawnTime = 1f;
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

	public float getSpawnTime()
	{
		return spawnTime;
	}

	public int getSpawnCount()
	{
		return spawnCount[currentStage - 1];
	}

	public int getSpawnCountLastStage()
	{
		return spawnCount[spawnCount.length - 1];
	}
}
