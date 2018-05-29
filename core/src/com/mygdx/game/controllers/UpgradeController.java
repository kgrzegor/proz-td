package com.mygdx.game.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.exceptions.GoldException;
import com.mygdx.game.exceptions.UpgradeException;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.GameLabel;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.screens.ui.InfoLabel;
import com.mygdx.game.services.GoldService;
import com.mygdx.game.services.UpgradeService;

/**
 * Every tower have its own upgrade controller. Shows upgrades menu with costs
 * and range indicator when tower is clicked (buttons are created in
 * constructor, they are only added to stage on click)
 */
public class UpgradeController
{
	/**
	 * Width and height of buttons in upgrade menu
	 */
	private final static int HEIGHT = 58;
	private final static int WIDTH = 120;

	private GameButton upgradeRange, upgradeDamage, upgradeFireRateCooldown, close;
	private GameLabel rangeCostLabel, damageCostLabel, fireRateCooldownCostLabel;

	private boolean menuOpened;
	private int menuX, menuY;
	private Tower tower;
	private Stage stage;
	private Image rangeIndicator;
	private GoldService goldService;
	private UpgradeService upgradeService;

	public UpgradeController(Tower tower, Stage stage, GoldService goldService)
	{
		this.stage = stage;
		this.tower = tower;
		this.goldService = goldService;
		this.upgradeService = new UpgradeService();
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

	/**
	 * Used when clicked on tower
	 */
	public void showMenu()
	{
		if (menuOpened)
			return;
		menuOpened = true;

		addStageActors();
		updateRangeIndicator();
		updateCostLabels();
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

	private void initUpgradeRange()
	{
		upgradeRange = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				showInfoLabel(upgradeRange());
			}
		}).position(menuX - WIDTH, menuY).height(HEIGHT).width(WIDTH).image("upgrade/range.png").build();

		rangeCostLabel = new GameLabel(menuX - WIDTH - 45, menuY + HEIGHT / 2);
		
	}

	private void initUpgradeDamage()
	{
		upgradeDamage = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				showInfoLabel(upgradeDamage());
			}
		}).position(menuX, menuY).height(HEIGHT).width(WIDTH).image("upgrade/damage.png").build();

		damageCostLabel = new GameLabel(menuX + WIDTH, menuY + HEIGHT / 2);
	}

	private void initUpgradeFireRateCooldown()
	{
		upgradeFireRateCooldown = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				showInfoLabel(upgradeFireRateCooldown());
			}
		}).position(menuX - WIDTH, menuY - HEIGHT).height(HEIGHT).width(WIDTH).image("upgrade/firerate.png").build();

		fireRateCooldownCostLabel = new GameLabel(menuX - WIDTH - 45, menuY - HEIGHT / 2);
	}

	private void initClose()
	{
		close = new GameButton.Builder(new IClickCallback()
		{
			public void onClick()
			{
				closeMenu();
			}
		}).position(menuX, menuY - HEIGHT).height(HEIGHT).width(WIDTH).image("upgrade/close.png").build();
	}

	private void initRangeIndicator()
	{
		rangeIndicator = new Image(new Texture("upgrade/rangeindicator.png"));
		rangeIndicator.setTouchable(Touchable.disabled);
		updateRangeIndicator();
	}

	/**
	 * When range is upgraded, indicator needs to be updates to show new range
	 */
	private void updateRangeIndicator()
	{
		rangeIndicator.setHeight(tower.getRange() * 2);
		rangeIndicator.setWidth(tower.getRange() * 2);
		rangeIndicator.setX(tower.getTowerX(), Align.center);
		rangeIndicator.setY(tower.getTowerY(), Align.center);
	}

	private String upgradeFireRateCooldown()
	{
		try
		{
			goldService.spendGold(upgradeService.getFireRateCooldownCost());
			upgradeService.nextfireRateCooldownLvl();
			tower.lowerFireRateCooldown();
			return String.format("Fire rate: %.2f", tower.getFireRateCooldown());
		} catch (GoldException e)
		{
			return e.getMessage();
		} catch (UpgradeException e)
		{
			goldService.addGold(upgradeService.getFireRateCooldownCost());
			return e.getMessage();
		}

	}

	protected String upgradeDamage()
	{
		try
		{
			goldService.spendGold(upgradeService.getDamageCost());
			upgradeService.nextDamageLvl();
			tower.addDamage();
			return ("Damage: " + tower.getDamage());
		} catch (GoldException e)
		{
			return e.getMessage();
		} catch (UpgradeException e)
		{
			goldService.addGold(upgradeService.getDamageCost());
			return e.getMessage();
		}
	}

	private String upgradeRange()
	{
		try
		{
			goldService.spendGold(upgradeService.getRangeCost());
			upgradeService.nextRangeLvl();
			tower.biggerRange();
			updateRangeIndicator();
			return ("Range: " + tower.getRange());
		} catch (GoldException e)
		{
			return e.getMessage();
		} catch (UpgradeException e)
		{
			goldService.addGold(upgradeService.getDamageCost());
			return e.getMessage();
		}
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

	protected void showInfoLabel(String info)
	{
		new InfoLabel(stage, menuX - Tower.WIDHT / 2, menuY - 100, info);
		updateCostLabels();
	}

	/**
	 * Removes Cost Labels when upgrade at max lvl
	 */
	private void updateCostLabels()
	{
		if (upgradeService.getDamageCost() > 0)
			damageCostLabel.setText(upgradeService.getDamageCost() + "g");
		else
			damageCostLabel.remove();

		if (upgradeService.getRangeCost() > 0)
			rangeCostLabel.setText(upgradeService.getRangeCost() + "g");
		else
			rangeCostLabel.remove();

		if (upgradeService.getFireRateCooldownCost() > 0)
			fireRateCooldownCostLabel.setText(upgradeService.getFireRateCooldownCost() + "g");
		else
			fireRateCooldownCostLabel.remove();
	}

}
