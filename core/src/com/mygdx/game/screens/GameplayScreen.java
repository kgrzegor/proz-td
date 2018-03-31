package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.ui.GameLabel;

public class GameplayScreen extends AbstractScreen{

	
	private GameLabel scoreLabel, heartLabel, stageLabel, timerLabel, goldLabel;
	private Button nextStageButton;
	
	public GameplayScreen(MyGdxGame game) 
	{
		super(game);
	}

	protected void init() 
	{		
		initLabels();
		initNextStageButton();
	}

	private void initLabels() 
	{
		scoreLabel = new GameLabel(stage,20,MyGdxGame.HEIGHT-20);
		heartLabel = new GameLabel(stage,150,MyGdxGame.HEIGHT-20);
		stageLabel = new GameLabel(stage,300,MyGdxGame.HEIGHT-20);
		timerLabel = new GameLabel(stage,400,MyGdxGame.HEIGHT-20);
		goldLabel = new GameLabel(stage,550,MyGdxGame.HEIGHT-20);
	}

	private void initNextStageButton() {
		nextStageButton = new Button(new ButtonStyle());
		nextStageButton.setWidth(100);
		nextStageButton.setHeight(20);
		nextStageButton.setX(700);
		nextStageButton.setY(MyGdxGame.HEIGHT-40);
		nextStageButton.setDebug(true);
		stage.addActor(nextStageButton);
		
		nextStageButton.addListener(new ClickListener()
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				game.nextStage();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		
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
