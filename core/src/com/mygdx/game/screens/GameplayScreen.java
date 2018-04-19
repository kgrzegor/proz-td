package com.mygdx.game.screens;


import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controllers.MobController;
import com.mygdx.game.screens.ui.GameLabel;
import com.mygdx.game.screens.ui.NextStageButton;

public class GameplayScreen extends AbstractScreen{

	
	private GameLabel scoreLabel, heartLabel, stageLabel, timerLabel, goldLabel;
	private NextStageButton nextStageButton;
	private MobController mobController;
	
	public GameplayScreen(MyGdxGame game) 
	{
		super(game);
	}

	protected void init() 
	{	
		game.setLastStage(30);
		game.setLivesLeft(3);
		game.setGold(700);
		initLabels();
		initNextStageButton(); 
		initMobController();		
	}

	private void initMobController()
	{
		mobController = new MobController(stage);
	}

	private void initLabels() 
	{
		scoreLabel = new GameLabel(stage,20,MyGdxGame.HEIGHT-20);
		heartLabel = new GameLabel(stage,150,MyGdxGame.HEIGHT-20);
		stageLabel = new GameLabel(stage,300,MyGdxGame.HEIGHT-20);
		timerLabel = new GameLabel(stage,400,MyGdxGame.HEIGHT-20);
		goldLabel = new GameLabel(stage,550,MyGdxGame.HEIGHT-20);
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
		heartLabel.setText("Lives: " + game.getLivesLeft() + " / 3");
		stageLabel.setText("Stage: " + game.getCurrentStage() + " / " + game.getLastStage());
		timerLabel.setText("Time: "  + game.getTimeUntilNextStage() + " s");
		goldLabel.setText("Gold: " + game.getGold() + " g");
		stage.act();
	}

}
