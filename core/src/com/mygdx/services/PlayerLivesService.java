package com.mygdx.services;

public class PlayerLivesService
{
	private int livesLeft;
	
	public PlayerLivesService()
	{
		setLivesLeft(3);
	}
	
	public void makeDamage()
	{
		--livesLeft;
		// TOOD if(lives == 0) end game
	}
	
	public int getLivesLeft()
	{
		return livesLeft;
	}
	public void setLivesLeft(int livesLeft)
	{
		this.livesLeft = livesLeft;
	}
	
}
