package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.MyGdxGame;

public class SplashScreen extends AbstractScreen
{

	private Texture splashImg;

	public SplashScreen(final MyGdxGame game)
	{
		super(game);
		init();

		Timer.schedule(new Task()
		{
			public void run()
			{
				game.setScreen(new GameplayScreen(game));
			}
		}, 1);
	}

	protected void init()
	{
		// TODO new asstets
		splashImg = new Texture("badlogic.jpg");
	}

	public void render(float delta)
	{
		super.render(delta);

		spriteBatch.begin();
		spriteBatch.draw(splashImg, 0, 0);
		spriteBatch.end();
	}
}
