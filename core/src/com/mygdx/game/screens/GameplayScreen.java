package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;

public class GameplayScreen extends AbstractScreen{

	
	private Label scoreLabel, heartLabel, stageLabel, timerLabel;
	private Button nextStageButton;
	
	public GameplayScreen(MyGdxGame game) 
	{
		super(game);
	}

	protected void init() 
	{		
		initScoreLabel();
		initHeartLabel();
		initStageLabel();
		initTimerLabel();
		initNextStageButton();
	}

	private void initNextStageButton() {
		nextStageButton = new Button(new ButtonStyle());
		nextStageButton.setWidth(100);
		nextStageButton.setHeight(20);
		nextStageButton.setX(600);
		nextStageButton.setY(MyGdxGame.HEIGHT-20);
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

	private void initScoreLabel() 
	{
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		scoreLabel = new Label("", labelStyle);
		scoreLabel.setX(20);
		scoreLabel.setY(MyGdxGame.HEIGHT-20);
		stage.addActor(scoreLabel);
	}
	private void initHeartLabel() 
	{
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		heartLabel = new Label("", labelStyle);
		heartLabel.setX(150);
		heartLabel.setY(MyGdxGame.HEIGHT-20);
		stage.addActor(heartLabel);
	}
	private void initStageLabel() 
	{
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		stageLabel = new Label("", labelStyle);
		stageLabel.setX(300);
		stageLabel.setY(MyGdxGame.HEIGHT-20);
		stage.addActor(stageLabel);
	}
	private void initTimerLabel() 
	{
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		timerLabel = new Label("", labelStyle);
		timerLabel.setX(400);
		timerLabel.setY(MyGdxGame.HEIGHT-20);
		stage.addActor(timerLabel);
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
		stage.act();
	}

}
