package com.mygdx.game.controllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.IClickCallback;

public class TowerController
{
	private boolean menuOpened;
	private int X, Y;
	private int height, width;
	private Tower tower;
	private Stage stage;
	private GameButton upgradeRadius;
	private GameButton upgradeDamage;
	private GameButton upgradeFireRateCooldown;
	private GameButton close;

	public TowerController(Tower tower, Stage stage, float towerX, float towerY)
	{
		this.X = (int) towerX;
		this.Y = (int) towerY;
		this.tower = tower;
		this.stage = stage;
		init();
	}

	private void init()
	{
		menuOpened = false;
		this.height = 58;
		this.width = 120;
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
		}).position(X, 100 + Y - height).height(height).width(width)
				// .debug(true)
				.image("close.png").build();

	}

	private void initUpgradeFireRateCooldown()
	{
		upgradeFireRateCooldown = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				tower.upgradeFireRateCooldown();
			}
		}).position(X - width, 100 + Y - height).height(height).width(width)
				// .debug(true)
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
		}).position(X, 100 + Y).height(height).width(width)
				// .debug(true)
				.image("damage.png").build();

	}

	private void initUpgradeRadius()
	{
		upgradeRadius = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				tower.upgradeRadius();
			}
		}).position(X - width, 100 + Y).height(height).width(width)
				// .debug(true)
				.image("range.png").build();

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
