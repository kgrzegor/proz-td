package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controllers.MobController;
import com.mygdx.game.controllers.FieldController;
import com.mygdx.game.screens.ui.GameLabel;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.screens.ui.NextStageButton;
import com.mygdx.game.services.GoldService;
import com.mygdx.game.services.PlayerLivesService;

public class GameplayScreen extends AbstractScreen
{

	private PlayerLivesService playerLivesService;
	private GoldService goldService;

	private Image mapImg;
	private GameLabel scoreLabel, heartLabel, stageLabel, timerLabel, goldLabel;
	private NextStageButton nextStageButton;
	private MobController mobController;
	@SuppressWarnings("unused")
	private FieldController towerController;

	public GameplayScreen(MyGdxGame game)
	{
		super(game);
	}

	protected void init()
	{
		game.setLastStage(30);
		initMapTexture();
		initLabels();
		initNextStageButton();
		initPlayerLivesService();
		initMobController();
		initGoldService();

		initTowerController();
	}

	private void initGoldService()
	{
		goldService = new GoldService();
	}

	private void initPlayerLivesService()
	{
		playerLivesService = new PlayerLivesService();
	}

	private void initMapTexture()
	{
		mapImg = new Image(new Texture("map.jpg"));
		stage.addActor(mapImg);

	}

	private void initMobController()
	{
		mobController = new MobController(stage, playerLivesService);
	}

	private void initLabels()
	{
		scoreLabel = new GameLabel(stage, 20, MyGdxGame.HEIGHT - 20);
		heartLabel = new GameLabel(stage, 150, MyGdxGame.HEIGHT - 20);
		stageLabel = new GameLabel(stage, 300, MyGdxGame.HEIGHT - 20);
		timerLabel = new GameLabel(stage, 400, MyGdxGame.HEIGHT - 20);
		goldLabel = new GameLabel(stage, 550, MyGdxGame.HEIGHT - 20);
	}

	private void initNextStageButton() // put this into controllers
	{
		nextStageButton = new NextStageButton(new IClickCallback()
		{
			public void onClick()
			{
				game.nextStage(mobController);
			}
		});

		stage.addActor(nextStageButton);
	}

	private void initTowerController() // put this into controllers
	{

		towerController = new FieldController(stage, goldService, mobController.getMobsList());
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
		scoreLabel.setText("Score: " + game.getPoints());
		heartLabel.setText("Lives: " + playerLivesService.getLivesLeft() + " / 3");
		stageLabel.setText("Stage: " + game.getCurrentStage() + " / " + game.getLastStage());
		timerLabel.setText("Time: " + game.getTimeUntilNextStage() + " s");
		goldLabel.setText("Gold: " + goldService.getGold() + " g");
		stage.act();
	}

	public PlayerLivesService getPlayerLivesService()
	{
		return playerLivesService;
	}

}
