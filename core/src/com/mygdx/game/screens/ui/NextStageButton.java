package com.mygdx.game.screens.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screens.ui.IClickCallback;

public class NextStageButton extends Button
{
	public NextStageButton(final IClickCallback callBack)
	{
		super(prepareStyle());

		init(callBack);
	}

	private void init(final IClickCallback callBack)
	{
		this.setWidth(100);
		this.setHeight(20);
		this.setX(700);
		this.setY(MyGdxGame.HEIGHT - 30);
		this.setDebug(true);

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
		// TODO image for button
		ButtonStyle buttonStyle = new ButtonStyle();
		return buttonStyle;
	}
}
