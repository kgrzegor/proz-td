package com.mygdx.game.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
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
	private int menuX, menuY;
	private int rangeCost, damageCost, fireRateCooldownCost;
	private Tower tower;
	private Stage stage;
	private GameButton upgradeRange, upgradeDamage, upgradeFireRateCooldown, close;
	private GameLabel rangeCostLabel, damageCostLabel, fireRateCooldownCostLabel;
	private GoldService goldService;
	private Image rangeIndicator;

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
		this.menuX = tower.getTowerX();
		this.menuY = 100 + tower.getTowerY();
		menuOpened = false;
		initUpgradeRange();
		initUpgradeDamage();
		initUpgradeFireRateCooldown();
		initClose();
		initRangeIndicator();
	}

	public void showMenu()
	{
		if (menuOpened)
			return;
		menuOpened = true;

		addStageActors();
		updateLabels();
	}

	private void addStageActors()
	{
		stage.addActor(rangeIndicator);

		stage.addActor(upgradeDamage);
		stage.addActor(upgradeFireRateCooldown);
		stage.addActor(upgradeRange);
		stage.addActor(close);

		stage.addActor(damageCostLabel);
		stage.addActor(rangeCostLabel);
		stage.addActor(fireRateCooldownCostLabel);
	}

	private void initRangeIndicator()
	{
		rangeIndicator = new Image(new Texture("rangeindicator.png"));
		rangeIndicator.setTouchable(Touchable.disabled);
		updateRangeIndicator();
	}

	private void updateRangeIndicator()
	{
		rangeIndicator.setHeight(tower.getRange() * 2);
		rangeIndicator.setWidth(tower.getRange() * 2);
		rangeIndicator.setX(tower.getTowerX(), Align.center);
		rangeIndicator.setY(tower.getTowerY(), Align.center);
	}

	private void initClose()
	{
		close = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				closeMenu();
			}
		}).position(menuX, menuY - HEIGHT).height(HEIGHT).width(WIDTH).image("close.png").build();
	}

	private void initUpgradeFireRateCooldown()
	{
		upgradeFireRateCooldown = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				showInfoLabel(upgradeFireRateCooldown());
			}
		}).position(menuX - WIDTH, menuY - HEIGHT).height(HEIGHT).width(WIDTH).image("firerate.png").build();

		fireRateCooldownCostLabel = new GameLabel(menuX - WIDTH - 35, menuY - HEIGHT / 2);
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
				return String.format("Fire rate: %.2f", tower.getFireRateCooldown());
				
			} else
			{
				return "Tower fire rate at max level!";
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
		}).position(menuX, menuY).height(HEIGHT).width(WIDTH).image("damage.png").build();

		damageCostLabel = new GameLabel(menuX + WIDTH, menuY + HEIGHT / 2);
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
				return "Tower damage at max level!";
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
		}).position(menuX - WIDTH, menuY).height(HEIGHT).width(WIDTH).image("range.png").build();

		rangeCostLabel = new GameLabel(menuX - WIDTH - 35, menuY + HEIGHT / 2);
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
				updateRangeIndicator();
				return ("Range: " + tower.getRange());
			} else
			{
				return "Tower range at max level!";
			}
		} catch (Exception e)
		{
			return e.getMessage();
		}
	}

	protected void showInfoLabel(String info)
	{
		new InfoLabel(stage, menuX - Tower.WIDHT / 2, menuY - 100, info);
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
		rangeIndicator.remove();
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
