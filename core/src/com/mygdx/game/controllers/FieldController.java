package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.entities.PowerUp;
import com.mygdx.game.entities.Mob;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.screens.ui.InfoLabel;
import com.mygdx.game.services.GoldService;

public class FieldController implements PowerUp
{
	private final int[] xCords = { 210, 115, 525, 335, 525, 850, 740, 975 };
	private final int[] yCords = { 160, 320, 245, 320, 440, 405, 185, 185 };
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
		towers = new Tower[nFields];
		initFieldButtons();
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
					buildTower(id);
				}
			}).position(xCords[id], yCords[id]).height(60).width(100).image("field.png").build();
		}

		for (int i = 0; i < fieldButtons.length; ++i)
			stage.addActor(fieldButtons[i]);
	}

	protected void buildTower(int id)
	{

		try
		{
			goldService.spendGold(500);
			towers[id] = new Tower(xCords[id], yCords[id], stage, mobsList, goldService);
			fieldButtons[id].setTouchable(Touchable.disabled);
			stage.addActor(towers[id]);
		} catch (Exception e)
		{
			new InfoLabel(stage, xCords[id], yCords[id] + 20, e.getMessage());
		}

	}

	public Tower[] getTowers()
	{
		return towers;
	}

	@Override
	public void popoutEffect(float strength)
	{
		for (Tower t : towers)
			if (t != null)
				t.bonusDamage(strength);
	}
}
