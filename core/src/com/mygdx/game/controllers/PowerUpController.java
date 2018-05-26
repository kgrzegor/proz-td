package com.mygdx.game.controllers;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.PowerUp;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.screens.ui.InfoLabel;
import com.mygdx.game.services.StageService;

public class PowerUpController
{
	private StageService stageService;
	private GameButton gameButton;
	private Stage stage;
	private boolean started;
	private final PowerUp[] popout;
	private Random rand;

	public PowerUpController(Stage stage, MyGdxGame game, final PowerUp[] popout)
	{
		this.stage = stage;
		this.popout = popout;
		this.started = false;
		this.rand = new Random();
		this.stageService = game.getStageService();
	}

	public void startPowerUps()
	{
		if (!started)
		Timer.schedule(new Task()
		{
			public void run()
			{
				initGameButton();
				stage.addActor(gameButton);
				initRemoveTimer();
			}
		}, 5, 5);
		
		started = true;
	}

	private void initRemoveTimer()
	{
		Timer.schedule(new Task()
		{
			public void run()
			{
				gameButton.remove();
			}
		}, 3);
	}

	private void initGameButton()
	{
		final int X = rand.nextInt(MyGdxGame.WIDTH - 200) + 100;
		final int Y = rand.nextInt(MyGdxGame.HEIGHT - 200) + 100;
		gameButton = new GameButton.Builder(new IClickCallback()
		{
			@Override
			public void onClick()
			{
				clicked(X, Y);
				
			}
		}).position(X, Y).height(25).width(28).image("popout.png").build();
	}

	protected void clicked(int x, int y)
	{
		String info = popout[rand.nextInt(popout.length)].powerUpEffect(stageService.getCurrentStage() * 10);
		gameButton.remove();
		new InfoLabel(stage, x-50, y, info);
		
	}
	
}
