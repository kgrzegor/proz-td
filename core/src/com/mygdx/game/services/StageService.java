package com.mygdx.game.services;

import com.mygdx.game.entities.enemies.MobType;

/**
 * Provides spawn counts and spawn times arrays, stage counter, wave types
 */
public class StageService
{
	private int currentStage;
	private int lastStage;
	private final float[] spawnTime = {3,3,1,5,3,3,5};
	private final int[] spawnCount = { 10, 10, 30, 15,5,10,1 };
	private final MobType[] waveType = { MobType.Demon, MobType.Yeti, MobType.Snail, MobType.RedDemon, MobType.BiggerYeti, MobType.Mummy, MobType.Boss};

	public StageService()
	{
		this.currentStage = 0;
		this.lastStage = spawnCount.length;
	}

	public boolean hasNextStage()
	{
		return !(currentStage == lastStage);
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

	public int getSpawnCount()
	{
		return spawnCount[currentStage - 1];
	}

	public int getSpawnCountLastStage()
	{
		return spawnCount[spawnCount.length - 1];
	}

	public MobType getWaveType(int inStage)
	{
		return waveType[inStage - 1];
	}

	public float getSpawnTime(int inStage)
	{
		return spawnTime[inStage - 1];
	}
}
