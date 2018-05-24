package com.mygdx.game.controllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.GameLabel;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.screens.ui.InfoLabel;
import com.mygdx.game.services.GoldService;

public class UpgradeController
{
	private final static int HEIGHT = 58;
	private final static int WIDTH = 120;

	private boolean menuOpened;
	private int towerX, towerY;
	private int rangeCost, damageCost, fireRateCooldownCost;
	private Tower tower;
	private Stage stage;
	private GameButton upgradeRange, upgradeDamage, upgradeFireRateCooldown, close;
	private GameLabel rangeCostLabel, damageCostLabel, fireRateCooldownCostLabel;
	private GoldService goldService;

	public UpgradeController(Tower tower, Stage stage, GoldService goldService)
	{
		this.stage = stage;
		this.tower = tower;
		this.goldService = goldService;
		this.rangeCost = 100;
		this.damageCost = 100;
		this.fireRateCooldownCost = 100;
		init();
	}

	private void init()
	{
		this.towerX = tower.getTowerX();
		this.towerY = 100 + tower.getTowerY();
		menuOpened = false;
	}

	public void showMenu()
	{
		if (menuOpened)
			return;
		menuOpened = true;

		initUpgradeRange();
		initUpgradeDamage();
		initUpgradeFireRateCooldown();
		initClose();
		initStageActors();
		updateLabels();
	}

	private void initStageActors()
	{
		stage.addActor(upgradeDamage);
		stage.addActor(upgradeFireRateCooldown);
		stage.addActor(upgradeRange);
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
		}).position(towerX, towerY - HEIGHT).height(HEIGHT).width(WIDTH).image("close.png").build();
	}

	private void initUpgradeFireRateCooldown()
	{
		upgradeFireRateCooldown = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				showInfoLabel(upgradeFireRateCooldown());
			}
		}).position(towerX - WIDTH, towerY - HEIGHT).height(HEIGHT).width(WIDTH).image("firerate.png").build();

		fireRateCooldownCostLabel = new GameLabel(stage, towerX - WIDTH - 35, towerY - HEIGHT / 2);
	}

	protected String upgradeFireRateCooldown()
	{
		try
		{
			if (tower.getFireRateCooldown() > 0.1)
			{
				goldService.spendGold(fireRateCooldownCost);
				fireRateCooldownCost += 100;
				tower.lowerFireRateCooldown();
				return ("Fire rate: " + tower.getFireRateCooldown());
			} else
			{
				return "Tower fire rate at max level";
			}
		} catch (Exception e)
		{
			return e.getMessage();
		}
	}

	private void initUpgradeDamage()
	{
		upgradeDamage = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				showInfoLabel(upgradeDamage());
			}
		}).position(towerX, towerY).height(HEIGHT).width(WIDTH).image("damage.png").build();

		damageCostLabel = new GameLabel(stage, towerX + WIDTH, towerY + HEIGHT / 2);
	}

	protected String upgradeDamage()
	{
		try
		{
			if (tower.getDamage() < 100)
			{
				goldService.spendGold(damageCost);
				damageCost += 100;
				tower.addDamage();
				return ("Damage: " + tower.getDamage());
			} else
			{
				return "Tower damage at max level";
			}
		} catch (Exception e)
		{
			return e.getMessage();
		}
	}

	private void initUpgradeRange()
	{
		upgradeRange = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				showInfoLabel(upgradeRange());
			}
		}).position(towerX - WIDTH, towerY).height(HEIGHT).width(WIDTH).image("range.png").build();

		rangeCostLabel = new GameLabel(stage, towerX - WIDTH - 35, towerY + HEIGHT / 2);
	}

	protected String upgradeRange()
	{
		try
		{
			if (tower.getRange() < 600)
			{
				goldService.spendGold(rangeCost);
				rangeCost += 100;
				tower.biggerRange();
				return ("Range: " + tower.getRange());
			} else
			{
				return "Tower range at max level";
			}
		} catch (Exception e)
		{
			return e.getMessage();
		}
	}

	protected void showInfoLabel(String info)
	{
		new InfoLabel(stage, towerX - Tower.WIDHT / 2, towerY - 100, info);
		updateLabels();
	}

	private void updateLabels()
	{
		damageCostLabel.setText(damageCost + "g");
		rangeCostLabel.setText(rangeCost + "g");
		fireRateCooldownCostLabel.setText(fireRateCooldownCost + "g");
	}

	protected void closeMenu()
	{
		upgradeRange.remove();
		upgradeDamage.remove();
		upgradeFireRateCooldown.remove();
		fireRateCooldownCostLabel.remove();
		damageCostLabel.remove();
		rangeCostLabel.remove();
		close.remove();
		menuOpened = false;
	}
}
