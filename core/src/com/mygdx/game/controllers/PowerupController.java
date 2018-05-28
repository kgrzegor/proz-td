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

/**
 * There should be observer somewhere
 */
public class PowerupController
{
	private final static int respawnTime = 5;
	private StageService stageService;
	private GameButton powerup;
	private Stage stage;
	private boolean started;
	private final PowerUp[] popout;
	private Random rand;

	public PowerupController(Stage stage, MyGdxGame game, final PowerUp[] popout)
	{
		this.stage = stage;
		this.popout = popout;
		this.started = false;
		this.rand = new Random();
		this.stageService = game.getStageService();
	}

	/**
	 * Every respawnTime seconds new powerUp appears in random place on map
	 */
	public void startPowerUps()
	{
		if (!started)
			Timer.schedule(new Task()
			{
				public void run()
				{
					newPowerup();
					stage.addActor(powerup);
					initRemoveTimer();
				}
			}, respawnTime, respawnTime);

		started = true;
	}

	/**
	 * After some time powerup disappears
	 */
	private void initRemoveTimer()
	{
		Timer.schedule(new Task()
		{
			public void run()
			{
				powerup.remove();
			}
		}, 3);
	}

	/**
	 * Choose random position for power up, needs to be added to stage. Position
	 * will be allways choosen at least 100px from map edge
	 */
	private void newPowerup()
	{
		final int X = rand.nextInt(MyGdxGame.WIDTH - 200) + 100;
		final int Y = rand.nextInt(MyGdxGame.HEIGHT - 200) + 100;

		powerup = new GameButton.Builder(new IClickCallback()
		{
			@Override
			public void onClick()
			{
				clicked(X, Y);

			}
		}).position(X, Y).height(25).width(28).image("map/powerup.png").build();
	}

	/**
	 * After being clicked one of the powerup effects is chosen, infolabel with
	 * effect is shown and powerup is removed. Needs to be change with observer
	 */
	protected void clicked(int x, int y)
	{
		String info = popout[rand.nextInt(popout.length)].powerUpEffect(stageService.getCurrentStage() * 10);
		powerup.remove();
		new InfoLabel(stage, x - 50, y, info);

	}

}
