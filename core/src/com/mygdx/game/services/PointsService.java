package com.mygdx.game.services;

public class PointsService
{
	private int points;

	public PointsService()
	{
		points = 0;
	}

	public int getPoints()
	{
		return points;
	}

	public void addPoints(int points)
	{
		this.points += points;
	}
}
