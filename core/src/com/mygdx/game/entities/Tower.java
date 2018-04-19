package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Tower extends Image
{
	private final static int WIDHT = 74;
	private final static int HEIGHT = 120;

	public Tower(int xCord, int yCord)
	{
		super(new Texture("tower.png"));
		init();

		this.setPosition((100 - 72) / 2 + xCord, yCord); // magic numbers
	}

	public void init()
	{
		this.setOrigin(WIDHT / 2, HEIGHT / 2);
		this.setSize(WIDHT, HEIGHT);
	}

}
