package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controllers.MobController;
import com.mygdx.game.entities.Tower;
import com.mygdx.game.controllers.FieldController;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.GameLabel;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.screens.ui.NextStageButton;
import com.mygdx.game.services.GoldService;
import com.mygdx.game.services.PlayerLivesService;
import com.mygdx.game.services.PointsService;

public class GameplayScreen extends AbstractScreen
{

	private PlayerLivesService playerLivesService;
	private GoldService goldService;

	private Image mapImg;
	private GameLabel scoreLabel, heartLabel, stageLabel, timerLabel, goldLabel;
	private NextStageButton nextStageButton;
	private GameButton freezeButton;
	private MobController mobController;
	private FieldController fieldController;
	private PointsService pointsService;
	private Tower[] towers;

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
		initGoldService();
		initPointServce();
		initMobController();
		initTowerController();
		towers = fieldController.getTowers();
		initFreezeButton();
	}

	private void initPointServce()
	{
		pointsService = new PointsService();		
	}

	private void initGoldService()
	{
		goldService = new GoldService();
	}

	private void initPlayerLivesService()
	{
		playerLivesService = new PlayerLivesService(game);
	}

	private void initMapTexture()
	{
		mapImg = new Image(new Texture("map.jpg"));
		stage.addActor(mapImg);
	}

	private void initMobController()
	{
		mobController = new MobController(stage, playerLivesService, goldService, pointsService);
	}

	private void initLabels()
	{
		scoreLabel = new GameLabel(stage, 20, MyGdxGame.HEIGHT - 20);
		heartLabel = new GameLabel(stage, 150, MyGdxGame.HEIGHT - 20);
		stageLabel = new GameLabel(stage, 300, MyGdxGame.HEIGHT - 20);
		timerLabel = new GameLabel(stage, 400, MyGdxGame.HEIGHT - 20);
		goldLabel = new GameLabel(stage, 550, MyGdxGame.HEIGHT - 20);
	}

	private void initNextStageButton()
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
	
	private void initFreezeButton()
	{
		freezeButton = new GameButton.Builder(new IClickCallback()
		{
			@Override
			public void onClick()
			{
				mobController.freezeAll();				
			}
		}).position(100, 100).height(100).width(100).debug(true).build();
		
		stage.addActor(freezeButton);
	}

	private void initTowerController()
	{

		fieldController = new FieldController(stage, goldService, mobController.getMobsList());
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
		scoreLabel.setText("Score: " + pointsService.getPoints());
		heartLabel.setText("Lives: " + playerLivesService.getLivesLeft() + " / 3");
		stageLabel.setText("Stage: " + game.getCurrentStage() + " / " + game.getLastStage());
		timerLabel.setText("Time: " + game.getTimeUntilNextStage() + " s");
		goldLabel.setText("Gold: " + goldService.getGold() + " g");

		for (int i = 0; i < towers.length; ++i)
		{
			if (towers[i] != null)
				towers[i].getProjectileController().checkHits();
		}
		stage.act();
	}

	public PlayerLivesService getPlayerLivesService()
	{
		return playerLivesService;
	}

}
