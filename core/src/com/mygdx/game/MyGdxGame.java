package com.mygdx.game;

import com.mygdx.game.controllers.MobController;
import com.mygdx.game.screens.MenuScreen;
import com.badlogic.gdx.Game;

public class MyGdxGame extends Game
{

	public final static String GAME_NAME = "Proz Tower Defence";

	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;

	private boolean paused;

	private int currentStage;
	private int lastStage;
	private int timeUntilNextStage;

	@Override
	public void create()
	{
		this.setScreen(new MenuScreen(this));
	}

	public void nextStage(MobController mobController)
	{
		// if(currentStage == 0)
		// Count to start
		if (currentStage < lastStage)
			++currentStage;

		mobController.startWave();
	}

	/*
	 * getters and setters
	 */
	public boolean isPaused()
	{
		return paused;
	}

	public void setPaused(boolean paused)
	{
		this.paused = paused;
	}

	public int getCurrentStage()
	{
		return currentStage;
	}

	public int getLastStage()
	{
		return lastStage;
	}

	public void setLastStage(int lastStage)
	{
		this.lastStage = lastStage;
	}

	public int getTimeUntilNextStage()
	{
		return timeUntilNextStage;
	}

	public void setTimeUntilNextStage(int timeUntilNextStage)
	{
		this.timeUntilNextStage = timeUntilNextStage;
	}

}
