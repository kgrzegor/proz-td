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

		endImg = new Image(new Texture("menu/" + text + ".png"));
		stage.addActor(endImg);

		init();
	}

	@Override
	protected void init()
	{

		initLabel();
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
		}).position(565, 200).width(150).height(64).image("menu/menubutton.png").build();

		stage.addActor(mainMenuButton);

	}

	private void initLabel()
	{
		GameLabel scoreLabel = new GameLabel(stage, 450, 370, "fontbig.fnt");
		scoreLabel
				.setText("Your Points: " + pointsService.getPoints() + "\nHighscore: " + pointsService.getHighscore());
	}

	public void render(float delta)
	{
		super.render(delta);

		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}

}
