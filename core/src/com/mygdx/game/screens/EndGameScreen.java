package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;

public class EndGameScreen extends AbstractScreen
{

	private Image endImg;

	public EndGameScreen(MyGdxGame game)
	{
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init()
	{
		endImg = new Image(new Texture("menu.png"));
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
