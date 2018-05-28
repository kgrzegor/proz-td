package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.IClickCallback;

/**
 * First screen in game. Show play and exit button
 */
public class MenuScreen extends AbstractScreen
{
	public MenuScreen(final MyGdxGame game)
	{
		super(game);
	}

	protected void init()
	{
		Image menuImg = new Image(new Texture("menu/menu.png"));
		stage.addActor(menuImg);

		initNewGame();
		initExitGame();
	}

	private void initExitGame()
	{
		GameButton exitGame = new GameButton.Builder(new IClickCallback()
		{

			@Override
			public void onClick()
			{
				Gdx.app.exit();
			}
		}).position(565, 200).height(64).width(150).debug(false).image("menu/exit.png").build();

		stage.addActor(exitGame);
	}

	private void initNewGame()
	{
		GameButton newGame = new GameButton.Builder(new IClickCallback()
		{
			@Override
			public void onClick()
			{
				game.setScreen(new GameplayScreen(game));

			}
		}).position(565, 300).height(64).width(150).debug(false).image("menu/start.png").build();

		stage.addActor(newGame);
	}

	public void render(float delta)
	{
		super.render(delta);

		spriteBatch.begin();
		stage.draw();
		spriteBatch.end();
	}
}
