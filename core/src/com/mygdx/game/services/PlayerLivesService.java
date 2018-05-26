package com.mygdx.game.services;

import com.mygdx.game.MyGdxGame;

public class PlayerLivesService
{
	private int livesLeft;

	public PlayerLivesService()
	{
		this.livesLeft = 3;
	}

	public void makeDamage()
	{
		--livesLeft;
	}
	
	public boolean gameOver()
	{
		return livesLeft == 0;
	}

	public int getLivesLeft()
	{
		return livesLeft;
	}

}
