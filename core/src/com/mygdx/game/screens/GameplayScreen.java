package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controllers.MobController;
import com.mygdx.game.controllers.PowerupController;
import com.mygdx.game.entities.PowerUp;
import com.mygdx.game.controllers.TowerController;
import com.mygdx.game.controllers.LabelsController;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.screens.ui.NextStageButton;
import com.mygdx.game.services.PlayerLivesService;
import com.mygdx.game.services.TimeService;

/**
 * Main screen in game. Creates core controllers and used as main loop in game.
 */
public class GameplayScreen extends AbstractScreen
{
	private Image mapImg;
	private NextStageButton nextStageButton;

	private PowerupController powerupController;
	private MobController mobController;
	private TowerController towerController;
	private LabelsController labelsController;

	private TimeService timeService;
	private PlayerLivesService playerLivesService;

	public GameplayScreen(MyGdxGame game)
	{
		super(game);
		init();
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
		powerupController = new PowerupController(stage, game, powerupList);
	}

	private void initMapTexture()
	{
		mapImg = new Image(new Texture("map/map.jpg"));
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
			endGame("youwon");

		if (playerLivesService.gameOver())
			endGame("gameover");

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
