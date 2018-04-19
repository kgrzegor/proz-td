package com.mygdx.game.controllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.screens.ui.FieldButton;
import com.mygdx.game.screens.ui.IClickCallback;

public class TowerController
{
	private final int[] xCords = { 300, 800 };
	private final int[] yCords = { 420, 420 };

	private FieldButton[] fieldButtons;
	private int debug;

	public TowerController(Stage stage)
	{
		init(stage);

	}

	private void init(Stage stage)
	{
		fieldButtons = new FieldButton[2];
		debug = 0;
		
		for (int i = 0; i < fieldButtons.length; ++i)
		fieldButtons[i] = new FieldButton(new IClickCallback()
		{
			public void onClick()
			{
				++debug;
				System.out.println(debug);
			}
		}, xCords[i], yCords[i]);


		for (int i = 0; i < fieldButtons.length; ++i)
			stage.addActor(fieldButtons[i]);
	}

}
