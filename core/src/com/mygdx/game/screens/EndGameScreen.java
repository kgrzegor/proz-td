package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.ui.GameLabel;

public class EndGameScreen extends AbstractScreen
{

	private Image endImg;

	public EndGameScreen(MyGdxGame game, String text)
	{
		super(game);
		
		GameLabel gameoverLabel = new GameLabel(stage, 200, 500, "fontbig.fnt");
		gameoverLabel.setText(text);

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
