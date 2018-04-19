package com.mygdx.game.controllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.screens.ui.FieldButton;
import com.mygdx.game.screens.ui.IClickCallback;

public class TowerController
{
	private final int[] xCords = { 300, 800 };
	private final int[] yCords = { 400, 400 };
	private final int nFields = 2;
	private FieldButton[] fieldButtons;
	private Tower[] towers;
	private Stage stage;

	public TowerController(Stage stage)
	{
		init(stage);
	}

	private void init(Stage stage)
	{
		this.stage = stage;
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
						// TODO check if player has gold and gold--
						towers[id] = new Tower(xCords[id], yCords[id]);
						stage.addActor(towers[id]);
					}

					// else
					// TODO open tower menu

				}
			}, xCords[i], yCords[i]);

		}

		for (int i = 0; i < fieldButtons.length; ++i)
			stage.addActor(fieldButtons[i]);
	}

}
