package com.mygdx.game.screens.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class GameLabel extends Label
{
	public GameLabel(Stage stage, int X, int Y)
	{
		super("", prepareLabelStyle());
		this.setX(X);
		this.setY(Y);
		stage.addActor(this);
	}

	private static LabelStyle prepareLabelStyle()
	{
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		return labelStyle;
	}

}