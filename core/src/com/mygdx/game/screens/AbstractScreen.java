package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;

/**
 * Screen used to simplify creating all other screens
 */
public abstract class AbstractScreen implements Screen
{

	protected MyGdxGame game;

	protected Stage stage;
	private OrthographicCamera camera;
	protected SpriteBatch spriteBatch;

	public AbstractScreen(MyGdxGame game)
	{
		this.game = game;
		createCamera();

		stage = new Stage(new StretchViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camera)); // stretched view
		spriteBatch = new SpriteBatch();
		Gdx.input.setInputProcessor(stage);
	}

	protected abstract void init();

	private void createCamera()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		camera.update();
	}

	public void render(float delta)
	{
		clearScreen();
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
	}

	public void show()
	{
	}

	private void clearScreen()
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void resume()
	{
		game.setPaused(false);
	}

	public void pause()
	{
		game.setPaused(true);
	}

	public void hide()
	{
	}

	public void dispose()
	{
		game.dispose();
	}

	public void resize(int width, int height)
	{
	}

}
