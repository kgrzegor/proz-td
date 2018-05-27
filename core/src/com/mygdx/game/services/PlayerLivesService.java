package com.mygdx.game.services;

public class PlayerLivesService
{
	private int livesLeft;
	private final int startingLives = 3;

	public PlayerLivesService()
	{
		this.livesLeft = startingLives;
	}

	public void makeDamage()
	{
		if (livesLeft > 0)
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

	public int getStartingLives()
	{
		return startingLives;
	}

}
