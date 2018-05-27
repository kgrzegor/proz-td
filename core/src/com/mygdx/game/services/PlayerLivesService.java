package com.mygdx.game.services;

/**
 * Provides everything to deal with player lives
 */
public class PlayerLivesService
{
	private int livesLeft;
	private final int startingLives;

	public PlayerLivesService(int startingLives)
	{
		this.livesLeft = this.startingLives = startingLives;
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
