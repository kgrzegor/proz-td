package com.mygdx.game;

import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.services.GoldService;
import com.mygdx.game.services.PlayerLivesService;
import com.mygdx.game.services.PointsService;
import com.mygdx.game.services.StageService;
import com.mygdx.game.services.TimeService;
import com.badlogic.gdx.Game;

/**
 * Main class in game
 **/
public class MyGdxGame extends Game
{
	/**
	 * Constants used by OS
	 **/
	public final static String GAME_NAME = "Proz Tower Defence";
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;

	private PlayerLivesService playerLivesService;
	private GoldService goldService;
	private StageService stageService;
	private PointsService pointsService;
	private TimeService timeService;

	private boolean paused;

	/**
	 * create() is called when game is opened or when new game is played
	 **/
	@Override
	public void create()
	{
		playerLivesService = new PlayerLivesService();
		goldService = new GoldService();
		pointsService = new PointsService();
		timeService = new TimeService();
		stageService = new StageService();
		this.setScreen(new MenuScreen(this));
	}

	public boolean isPaused()
	{
		return paused;
	}

	public void setPaused(boolean paused)
	{
		this.paused = paused;
	}

	/******
	 * GETTERS used whenever reference to any service is needed, every bigger class
	 * should have game class reference
	 *******/
	public PlayerLivesService getPlayerLivesService()
	{
		return playerLivesService;
	}

	public GoldService getGoldService()
	{
		return goldService;
	}

	public StageService getStageService()
	{
		return stageService;
	}

	public PointsService getPointsService()
	{
		return pointsService;
	}

	public TimeService getTimeService()
	{
		return timeService;
	}
}
