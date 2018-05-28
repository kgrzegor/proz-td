package com.mygdx.game.screens;

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
	private Image menuImg;
	private GameButton newGame;

	public MenuScreen(final MyGdxGame game)
	{
		super(game);
	}

	protected void init()
	{
		menuImg = new Image(new Texture("map/menu.png"));
		stage.addActor(menuImg);

		newGame = new GameButton.Builder(new IClickCallback()
		{
			@Override
			public void onClick()
			{
				game.setScreen(new GameplayScreen(game));

			}
		}).position(500, 300).height(66).width(150).debug(false).image("start.png").build();

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
