package com.mygdx.game.services;

public class GoldService
{
	private int gold;

	public GoldService()
	{
		gold = 1000; // magic numbers
	}

	public int getGold()
	{
		return gold;
	}

	public void addGold(int gold)
	{
		this.gold += gold;
	}

	public boolean spendGold(int cost)
	{
		if (gold >= cost)
		{
			gold -= cost;
			return true;
		} else
		{
			return false;
		}
	}
}
