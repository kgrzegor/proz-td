package com.mygdx.game.services;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.EndGameScreen;

public class PlayerLivesService
{
	private int livesLeft;
	MyGdxGame game;

	public PlayerLivesService(MyGdxGame game)
	{
		this.livesLeft = 3;
		this.game = game;
	}

	public void makeDamage()
	{
		--livesLeft;
		if(livesLeft == 0)
			game.setScreen(new EndGameScreen(game));
	}

	public int getLivesLeft()
	{
		return livesLeft;
	}

}
