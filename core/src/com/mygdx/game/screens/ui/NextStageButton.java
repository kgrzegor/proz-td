package com.mygdx.game.screens.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.ui.IClickCallback;

/**
 * Button used only for calling nest stage/wave, have different image when up
 * and down
 */
public class NextStageButton extends Button
{
	public NextStageButton(final IClickCallback callBack)
	{
		super(prepareStyle());

		init(callBack);
	}

	private void init(final IClickCallback callBack)
	{
		this.setWidth(64);
		this.setHeight(32);
		this.setX(575);
		this.setY(MyGdxGame.HEIGHT - 38);

		this.addListener(new ClickListener()
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				callBack.onClick();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
	}

	private static ButtonStyle prepareStyle()
	{
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui-gray.atlas"));
		Skin skin = new Skin(atlas);
		ButtonStyle buttonStyle = new ButtonStyle();
		buttonStyle.up = skin.getDrawable("button_03");
		buttonStyle.down = skin.getDrawable("button_01");
		return buttonStyle;
	}
}
