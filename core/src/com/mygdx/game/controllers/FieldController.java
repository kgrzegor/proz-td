package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.entities.Mob;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.screens.ui.FieldButton;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.services.GoldService;

public class FieldController
{
	private final int[] xCords = { 300, 800 };
	private final int[] yCords = { 400, 400 };
	private final int nFields = 2;
	private FieldButton[] fieldButtons;
	private Tower[] towers;
	private Stage stage;
	private GoldService goldService;
	private ArrayList<Mob> mobsList;

	public FieldController(Stage stage, GoldService goldService, ArrayList<Mob> mobsList)
	{
		this.stage = stage;
		this.goldService = goldService;
		this.mobsList = mobsList;
		init();
	}

	private void init()
	{
		
		initFieldButtons();
		initTowers();
	}

	private void initTowers()
	{
		towers = new Tower[nFields];
	}

	private void initFieldButtons()
	{
		fieldButtons = new FieldButton[nFields];
		for (int i = 0; i < nFields; ++i)
		{
			final int id = i;
			fieldButtons[i] = new FieldButton(new IClickCallback()
			{
				public void onClick()
				{
					if (towers[id] == null)
					{
						if (goldService.spendGold(500)) //magic numbers
						{
							towers[id] = new Tower(xCords[id], yCords[id], stage, mobsList);
							stage.addActor(towers[id]);
						}
						
					}

					// else
					// TODO open tower menu

				}
			}, xCords[i], yCords[i]);

		}

		for (int i = 0; i < fieldButtons.length; ++i)
			stage.addActor(fieldButtons[i]);
	}

	public Tower[] getTowers()
	{
		return towers;
	}
}
