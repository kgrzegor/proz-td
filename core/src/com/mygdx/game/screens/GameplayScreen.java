package com.mygdx.game.screens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.controllers.MobController;
import com.mygdx.game.screens.ui.EmptyFieldButton;
import com.mygdx.game.screens.ui.GameLabel;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.screens.ui.NextStageButton;
import com.mygdx.services.PlayerLivesService;

public class GameplayScreen extends AbstractScreen{

	
	private PlayerLivesService playerLivesService;
	
	private Image mapImg;
	private GameLabel scoreLabel, heartLabel, stageLabel, timerLabel, goldLabel;
	private NextStageButton nextStageButton;
	private MobController mobController;
	private EmptyFieldButton [] fieldButtons;
	
	
	private int debug;
	
	public GameplayScreen(MyGdxGame game) 
	{
		super(game);
	}

	protected void init() 
	{	
		game.setLastStage(30);
		game.setGold(700);
		initMapTexture();
		initLabels();
		initNextStageButton(); 
		initEmptyFieldButtons();
		initPlayerLivesService();
		initMobController();	
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

	private void initMobController() // put this somewhere
	{
		mobController = new MobController(stage,playerLivesService);
	}

	private void initLabels() // put this into UI
	{
		scoreLabel = new GameLabel(stage,20,MyGdxGame.HEIGHT-20);
		heartLabel = new GameLabel(stage,150,MyGdxGame.HEIGHT-20);
		stageLabel = new GameLabel(stage,300,MyGdxGame.HEIGHT-20);
		timerLabel = new GameLabel(stage,400,MyGdxGame.HEIGHT-20);
		goldLabel = new GameLabel(stage,550,MyGdxGame.HEIGHT-20);
	}

	private void initNextStageButton()  //put this into controllers
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
	private void initEmptyFieldButtons()
	{
		fieldButtons = new EmptyFieldButton[2];
		debug = 0;
		fieldButtons[0] = new EmptyFieldButton(new IClickCallback() 
		{
			public void onClick()
			{
				++debug;
				System.out.println(debug);
			}
		},100,100);
		fieldButtons[1] = new EmptyFieldButton(new IClickCallback() 
		{
			public void onClick()
			{
				++debug;
				System.out.println(debug);
			}
		},300,200);
		
		for (int i = 0; i < fieldButtons.length; ++i)
		stage.addActor(fieldButtons[i]);
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
		timerLabel.setText("Time: "  + game.getTimeUntilNextStage() + " s");
		goldLabel.setText("Gold: " + game.getGold() + " g");
		stage.act();
	}

	public PlayerLivesService getPlayerLivesService()
	{
		return playerLivesService;
	}

}
