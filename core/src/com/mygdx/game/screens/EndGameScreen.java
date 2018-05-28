package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.GameLabel;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.services.PointsService;

/**
 * Shown when game is ended. Shows current result and highscore
 */
public class EndGameScreen extends AbstractScreen
{

	private Image endImg;
	private PointsService pointsService;

	public EndGameScreen(MyGdxGame game, String text)
	{
		super(game);
		pointsService = game.getPointsService();

		initLabels(text);
		initMainMenuButton();
	}

	private void initMainMenuButton()
	{
		GameButton mainMenuButton = new GameButton.Builder(new IClickCallback()
		{

			@Override
			public void onClick()
			{
				game.create();

			}
		}).debug(true).position(500, 200).width(50).height(50).build();

		stage.addActor(mainMenuButton);

	}

	private void initLabels(String text)
	{
		GameLabel gameoverLabel = new GameLabel(stage, 200, 500, "fontbig.fnt");
		gameoverLabel.setText(text);

		GameLabel scoreLabel = new GameLabel(stage, 200, 400);
		scoreLabel
				.setText("Your Points: " + pointsService.getPoints() + "\nHighscore: " + pointsService.getHighscore());

	}

	@Override
	protected void init()
	{
		endImg = new Image(new Texture("map/end.png"));
		stage.addActor(endImg);
	}

	public void render(float delta)
	{
		super.render(delta);

		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

}
