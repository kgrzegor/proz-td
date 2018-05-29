package com.mygdx.game.screens.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Basic game label
 */
public class GameLabel extends Label
{
	public GameLabel(int X, int Y)
	{
		super("", prepareLabelStyle("font.fnt"));
		this.setX(X);
		this.setY(Y);
	}

	public GameLabel(Stage stage, int X, int Y)
	{
		this(X, Y);
		stage.addActor(this);
	}

	public GameLabel(Stage stage, int X, int Y, String font)
	{
		super("", prepareLabelStyle(font));
		this.setX(X);
		this.setY(Y);
		stage.addActor(this);
	}

	private static LabelStyle prepareLabelStyle(String font)
	{
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont(Gdx.files.internal(font));
		return labelStyle;
	}

}