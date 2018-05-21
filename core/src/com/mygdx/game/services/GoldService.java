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

	public void spendGold(int cost) throws Exception
	{
		if (gold >= cost)
			gold -= cost;
		else
			throw new Exception("Not enough gold!\nYou need " + (cost - gold) + "g more.");
	}
}
