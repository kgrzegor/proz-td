package com.mygdx.services;

public class GoldService
{
	private int gold;

	public GoldService()
	{
		setGold(700); // magic numbers
	}

	public int getGold()
	{
		return gold;
	}

	public void setGold(int gold)
	{
		this.gold = gold;
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
