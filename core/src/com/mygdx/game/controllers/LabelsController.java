package com.mygdx.game.controllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.ui.GameLabel;
import com.mygdx.game.services.GoldService;
import com.mygdx.game.services.PlayerLivesService;
import com.mygdx.game.services.PointsService;
import com.mygdx.game.services.StageService;
import com.mygdx.game.services.TimeService;

/**
 * Creates all labels in UI and updates them
 **/
public class LabelsController
{
	private GameLabel scoreLabel, heartLabel, stageLabel, timerLabel, goldLabel;

	private PlayerLivesService playerLivesService;
	private GoldService goldService;
	private StageService stageService;
	private PointsService pointsService;
	private TimeService timeService;

	public LabelsController(Stage stage, MyGdxGame game)
	{
		initLabels(stage);
		playerLivesService = game.getPlayerLivesService();
		goldService = game.getGoldService();
		stageService = game.getStageService();
		pointsService = game.getPointsService();
		timeService = game.getTimeService();
	}

	private void initLabels(Stage stage)
	{
		scoreLabel = new GameLabel(stage, 10, MyGdxGame.HEIGHT - 20);
		heartLabel = new GameLabel(stage, 110, MyGdxGame.HEIGHT - 20);
		stageLabel = new GameLabel(stage, 210, MyGdxGame.HEIGHT - 20);
		timerLabel = new GameLabel(stage, 315, MyGdxGame.HEIGHT - 20);
		goldLabel = new GameLabel(stage, 410, MyGdxGame.HEIGHT - 20);
	}

	/**
	 * Updates all labels in game, if game is in last stage timerLabel is removed
	 **/
	public void updateLabels()
	{
		scoreLabel.setText("Score: " + pointsService.getPoints());
		heartLabel
				.setText("Lives: " + playerLivesService.getLivesLeft() + " / " + playerLivesService.getStartingLives());
		stageLabel.setText("Stage: " + stageService.getCurrentStage() + " / " + stageService.getLastStage());
		goldLabel.setText("Gold: " + goldService.getGold() + " g");

		if (stageService.hasNextStage())
			timerLabel.setText("Time: " + timeService.getTime() + " s");
		else
			timerLabel.remove();
	}
}
