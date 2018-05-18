package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.entities.Entities;
import com.mygdx.game.entities.Mob;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.services.GoldService;

public class FieldController implements Entities
{
	private final int[] xCords = { 210, 115, 525, 335, 525, 850, 740, 975 };
	private final int[] yCords = { 560, 400, 475, 400, 280, 315, 535, 535 };
	private final int nFields = 8;
	private GameButton[] fieldButtons;
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
		fieldButtons = new GameButton[nFields];
		for (int i = 0; i < nFields; ++i)
		{
			final int id = i;
			fieldButtons[id] = new GameButton.Builder(new IClickCallback()
			{
				public void onClick()
				{
					System.out.println("My id: " + id);
					if (towers[id] == null)
					{
						if (goldService.spendGold(10)) // magic numbers
						{
							towers[id] = new Tower(xCords[id], 720 - yCords[id], stage, mobsList);
							stage.addActor(towers[id]);
						}
					}

				}
			})
			.position(xCords[i], 720 - yCords[i])
			.height(60)
			.width(100)
			.image("field.png")
			//.debug(true)
			.build();
		}

		for (int i = 0; i < fieldButtons.length; ++i)
			stage.addActor(fieldButtons[i]);
	}

	public Tower[] getTowers()
	{
		return towers;
	}

	@Override
	public void popoutEffect(float strength)
	{
		for(Tower t : towers)
			if (t != null)
				t.bonusDamage(strength);
	}
}
