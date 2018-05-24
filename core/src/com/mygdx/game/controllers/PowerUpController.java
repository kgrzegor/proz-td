package com.mygdx.game.controllers;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.PowerUp;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.services.StageService;

public class PowerUpController
{
	private StageService stageService;
	private GameButton gameButton;
	private Stage stage;
	private final PowerUp[] popout;
	private Random rand;

	public PowerUpController(Stage stage, final PowerUp[] popout, StageService stageService)
	{
		this.stage = stage;
		this.popout = popout;
		this.rand = new Random();
		this.stageService = stageService;
	}

	public void startPowerUps()
	{
		Timer.schedule(new Task()
		{
			public void run()
			{
				initGameButton();
				stage.addActor(gameButton);
				initRemoveTimer();
			}
		}, 5, 5);
	}

	protected void initRemoveTimer()
	{
		Timer.schedule(new Task()
		{
			public void run()
			{
				gameButton.remove();
			}
		}, 3);
	}

	protected void initGameButton()
	{
		int X = rand.nextInt(MyGdxGame.WIDTH - 200) + 100;
		int Y = rand.nextInt(MyGdxGame.HEIGHT) + 100;
		gameButton = new GameButton.Builder(new IClickCallback()
		{
			@Override
			public void onClick()
			{
				popout[rand.nextInt(popout.length)].popoutEffect(stageService.getCurrentStage() * 10);
				gameButton.remove();
			}
		}).position(X, Y).height(25).width(28).image("popout.png").build();
	}
}
