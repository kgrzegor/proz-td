package com.mygdx.game;

import com.mygdx.game.controllers.MobController;
import com.mygdx.game.screens.SplashScreen;
import com.badlogic.gdx.Game;


public class MyGdxGame extends Game {
	
	public final static String GAME_NAME = "Proz Tower Defence";
	
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;
	
	private boolean paused;
	
	private int points;
	private int currentStage;
	private int lastStage;
	private int timeUntilNextStage;
	private int livesLeft;
	private int gold;
	
	@Override
	public void create () {
		this.setScreen(new SplashScreen(this));
	}


	public void addPoints(int value) {
		points += value;
	}

	public void nextStage(MobController mobController) {
		//if(currentStage == 0)
		// Count to start	
		if(currentStage < lastStage )
			++currentStage;
		
		mobController.startWave();
	}
	
	/*
	 * getters and setters
	 */
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}


	public int getPoints() {
		return points;
	}


	public int getCurrentStage() {
		return currentStage;
	}


	public int getLastStage() {
		return lastStage;
	}


	public void setLastStage(int lastStage) {
		this.lastStage = lastStage;
	}


	public int getTimeUntilNextStage() {
		return timeUntilNextStage;
	}


	public void setTimeUntilNextStage(int timeUntilNextStage) {
		this.timeUntilNextStage = timeUntilNextStage;
	}


	public int getLivesLeft() {
		return livesLeft;
	}


	public void setLivesLeft(int livesLeft) {
		this.livesLeft = livesLeft;
	}


	public int getGold()
	{
		return gold;
	}


	public void setGold(int gold)
	{
		this.gold = gold;
	}


}
