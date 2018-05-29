package com.mygdx.game.controllers;

import java.util.LinkedList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.entities.enemies.Mob;
import com.mygdx.game.exceptions.GoldException;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.screens.ui.InfoLabel;
import com.mygdx.game.services.GoldService;

/**
 * Creates fields buttons where tower can be build. Can iterate over every tower
 * and check hits
 */
public class TowerController
{
	/**
	 * Specify where towers can be build on map
	 */
	private final int[] xCords = { 210, 115, 525, 335, 525, 850, 740, 975 };
	private final int[] yCords = { 160, 320, 245, 320, 440, 405, 185, 185 };
	private final int nFields = xCords.length;

	private final int towerCost = 500;

	private GameButton[] fieldButtons;
	private Tower[] towers;
	private Stage stage;
	private GoldService goldService;
	private LinkedList<Mob> mobsList;
	private PowerupController powerupController;

	public TowerController(Stage stage, MyGdxGame game, LinkedList<Mob> mobsList, PowerupController powerupController)
	{
		this.stage = stage;
		this.goldService = game.getGoldService();
		this.mobsList = mobsList;
		this.powerupController = powerupController;
		towers = new Tower[nFields];
		initFieldButtons();
	}

	/**
	 * Creates field buttons in specified places, adds them to stage
	 */
	private void initFieldButtons()
	{
		fieldButtons = new GameButton[nFields];
		for (int i = 0; i < nFields; ++i)
		{
			final int id = i;
			fieldButtons[id] = new GameButton.Builder(new IClickCallback()
			{
				public void onClick()
				{
					buildTower(id);
				}
			}).position(xCords[id], yCords[id]).height(60).width(100).image("map/field.png").build();
		}

		for (int i = 0; i < fieldButtons.length; ++i)
			stage.addActor(fieldButtons[i]);
	}

	/**
	 * Try to build tower if player have enough gold, otherwise shows info label
	 * with error message. If managed to build, adds tower to stage and disable
	 * field button
	 * 
	 * @param id
	 *            Field id where tower will be build
	 */
	private void buildTower(int id)
	{
		try
		{
			goldService.spendGold(towerCost);
			towers[id] = new Tower(xCords[id], yCords[id], stage, mobsList, goldService);
			fieldButtons[id].setTouchable(Touchable.disabled);
			powerupController.addAffected(towers[id]);
			stage.addActor(towers[id]);
		} catch (GoldException e)
		{
			new InfoLabel(stage, xCords[id], yCords[id] + 20, e.getMessage());
		}
	}

	/**
	 * Iterates over every built tower and check hits with its projectiles. Detects
	 * every possible collision
	 */
	public void checkHits()
	{
		for (int i = 0; i < towers.length; ++i)
		{
			if (towers[i] != null)
				towers[i].getProjectileController().checkHits();
		}
	}

}
