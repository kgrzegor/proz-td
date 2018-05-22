package com.mygdx.game;

import com.mygdx.game.screens.MenuScreen;
import com.badlogic.gdx.Game;

public class MyGdxGame extends Game
{

	public final static String GAME_NAME = "Proz Tower Defence";

	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;

	private boolean paused;

	@Override
	public void create()
	{
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
}
