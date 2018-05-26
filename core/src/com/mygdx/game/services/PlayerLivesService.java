package com.mygdx.game.services;


public class PlayerLivesService
{
	private int livesLeft;

	public PlayerLivesService()
	{
		this.livesLeft = 3;
	}

	public void makeDamage()
	{
		if(livesLeft > 0)
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
