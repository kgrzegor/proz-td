package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controllers.MobController;
import com.mygdx.game.controllers.PowerUpController;
import com.mygdx.game.entities.PowerUp;
import com.mygdx.game.controllers.TowerController;
import com.mygdx.game.controllers.LabelsController;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.screens.ui.NextStageButton;
import com.mygdx.game.services.PlayerLivesService;
import com.mygdx.game.services.TimeService;

public class GameplayScreen extends AbstractScreen
{
	private Image mapImg;
	private NextStageButton nextStageButton;

	private PowerUpController powerupController;
	private MobController mobController;
	private TowerController towerController;
	private LabelsController labelsController;

	private TimeService timeService;
	private PlayerLivesService playerLivesService;

	public GameplayScreen(MyGdxGame game)
	{
		super(game);
	}

	protected void init()
	{
		initMapTexture();
		initNextStageButton();
		
		timeService = game.getTimeService();
		playerLivesService = game.getPlayerLivesService();
		
		labelsController = new LabelsController(stage, game);
		mobController = new MobController(stage, game);
		towerController = new TowerController(stage, game, mobController.getMobsList());

		final PowerUp[] powerupList = { mobController, towerController };
		powerupController = new PowerUpController(stage, game, powerupList);
	}

	private void initMapTexture()
	{
		mapImg = new Image(new Texture("map.jpg"));
		stage.addActor(mapImg);
	}

	private void initNextStageButton()
	{
		nextStageButton = new NextStageButton(new IClickCallback()
		{
			public void onClick()
			{
				powerupController.startPowerUps();
				mobController.startWave();
			}
		});

		stage.addActor(nextStageButton);
	}

	public void render(float delta)
	{
		super.render(delta);
		update();

		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

	private void update()
	{
		towerController.checkHits();
		labelsController.updateLabels();
		
		if (timeService.getTime() == 0)
			mobController.startWave();

		if (mobController.allEnemyKilled())
			endGame("You won");

		if (playerLivesService.gameOver())
			endGame("Game over!");

		stage.act();
	}

	private void endGame(final String text)
	{
		Timer.schedule(new Task()
		{
			public void run()
			{
				game.setScreen(new EndGameScreen(game, text));
			}
		}, 3);
	}
}
