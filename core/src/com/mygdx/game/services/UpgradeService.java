package com.mygdx.game.services;

import com.mygdx.game.exceptions.UpgradeException;

public class UpgradeService
{
	private static final int[] rangeCost = { 100, 150, 200, 300, 500, 1000, 2000, 0};
	private static final int[] damageCost = { 200, 300, 400, 500, 1000, 2000, 3000, 5000, 0};
	private static final int[] fireRateCooldownCost = { 200, 500, 1000, 1500, 2000, 5000, 9000, 0};

	private int rangeLvl, damageLvl, fireRateCooldownLvl;

	public UpgradeService()
	{
		this.rangeLvl = 0;
		this.damageLvl = 0;
		this.fireRateCooldownLvl = 0;
	}

	public int getRangeCost()
	{
		return rangeCost[rangeLvl];
	}

	public void nextRangeLvl() throws UpgradeException
	{
		if (rangeLvl < rangeCost.length - 1)
			rangeLvl++;
		else
			throw new UpgradeException("range");
	}

	public int getDamageCost()
	{
		return damageCost[damageLvl];
	}

	public void nextDamageLvl() throws UpgradeException
	{
		if (damageLvl < damageCost.length - 1)
			damageLvl++;
		else
			throw new UpgradeException("damage");
	}

	public int getFireRateCooldownCost()
	{
		return fireRateCooldownCost[fireRateCooldownLvl];
	}

	public void nextfireRateCooldownLvl() throws UpgradeException
	{
		if (fireRateCooldownLvl < fireRateCooldownCost.length - 1)
			fireRateCooldownLvl++;
		else
			throw new UpgradeException("fire rate");
	}
}
