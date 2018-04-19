package com.mygdx.game.screens.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class FieldButton extends Button
{
	public FieldButton (final IClickCallback callBack, int X, int Y)
	{
		super(prepareStyle());
		
		init(callBack);
		
		this.setX(X);
		this.setY(Y);
	}

	private void init(final IClickCallback callBack)
	{
		this.setWidth(100);
		this.setHeight(100);
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
		//TODO image for button
		ButtonStyle buttonStyle = new ButtonStyle();
		return buttonStyle;
	}
}
