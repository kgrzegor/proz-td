package com.mygdx.game.services;

import com.mygdx.game.exceptions.GoldException;

/**
 * Provides adding and spending gold
 */
public class GoldService
{
	private int gold;

	public GoldService(int gold)
	{
		this.gold = gold;
	}

	public int getGold()
	{
		return gold;
	}

	public void addGold(int gold)
	{
		this.gold += gold;
	}

	public void spendGold(int cost) throws GoldException
	{
		if (gold >= cost)
			gold -= cost;
		else
			throw new GoldException(cost - gold);
	}
}
