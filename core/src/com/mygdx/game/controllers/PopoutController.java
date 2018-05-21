package com.mygdx.game.controllers;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Entities;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.IClickCallback;

public class PopoutController
{
	private GameButton gameButton;
	private Stage stage;
	private final Entities[] popout;
	private Random rand;

	public PopoutController(Stage stage, final Entities[] popout)
	{
		this.stage = stage;
		this.popout = popout;
		this.rand = new Random();

	}

	public void startPopouts()
	{
		Timer.schedule(new Task()
		{
			public void run()
			{

				gameButton = new GameButton.Builder(new IClickCallback()
				{
					@Override
					public void onClick()
					{
						popout[rand.nextInt(2)].popoutEffect(30);
						gameButton.remove();
					}
				}).position(rand.nextInt(1080) + 100, rand.nextInt(520) + 100).height(25).width(28).image("popout.png")
						.debug(false).build();

				stage.addActor(gameButton);

				Timer.schedule(new Task()
				{
					public void run()
					{
						gameButton.remove();
					}
				}, 3);
			}

		}

				, 5, 5);
	}

}
