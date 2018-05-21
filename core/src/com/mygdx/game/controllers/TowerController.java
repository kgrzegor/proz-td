package com.mygdx.game.controllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.IClickCallback;

public class TowerController
{
	private boolean menuOpened;
	private int towerX, towerY;
	private int buttonHeight, buttonWidth;
	private Tower tower;
	private Stage stage;
	private GameButton upgradeRadius, upgradeDamage, upgradeFireRateCooldown, close;

	public TowerController(Tower tower, Stage stage)
	{
		this.stage = stage;
		this.tower = tower;
		init();
	}

	private void init()
	{
		this.towerX = tower.getTowerX();
		this.towerY = 100 + tower.getTowerY();
		menuOpened = false;
		this.buttonHeight = 58;
		this.buttonWidth = 120;
	}

	public void showMenu()
	{
		if (menuOpened)
			return;
		menuOpened = true;

		initUpgradeRadius();
		initUpgradeDamage();
		initUpgradeFireRateCooldown();
		initClose();
		initStageActors();
	}

	private void initStageActors()
	{
		stage.addActor(upgradeDamage);
		stage.addActor(upgradeFireRateCooldown);
		stage.addActor(upgradeRadius);
		stage.addActor(close);
	}

	private void initClose()
	{

		close = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				closeMenu();
			}
		}).position(towerX, towerY - buttonHeight).height(buttonHeight).width(buttonWidth).image("close.png").build();

	}

	private void initUpgradeFireRateCooldown()
	{
		upgradeFireRateCooldown = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				tower.upgradeFireRateCooldown();
			}
		}).position(towerX - buttonWidth, towerY - buttonHeight).height(buttonHeight).width(buttonWidth)
				.image("firerate.png").build();

	}

	private void initUpgradeDamage()
	{
		upgradeDamage = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				tower.upgradeDamage();
			}
		}).position(towerX, towerY).height(buttonHeight).width(buttonWidth).image("damage.png").build();

	}

	private void initUpgradeRadius()
	{
		upgradeRadius = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				tower.upgradeRadius();
			}
		}).position(towerX - buttonWidth, towerY).height(buttonHeight).width(buttonWidth).image("range.png").build();

	}

	protected void closeMenu()
	{
		upgradeRadius.remove();
		upgradeDamage.remove();
		upgradeFireRateCooldown.remove();
		close.remove();
		menuOpened = false;
	}
}
