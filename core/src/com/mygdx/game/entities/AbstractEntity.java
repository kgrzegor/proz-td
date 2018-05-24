package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class AbstractEntity extends Image
{

	public AbstractEntity(String name, int X, int Y, int widht, int height)
	{
		super(new Texture(name));

		this.setOrigin(widht / 2, height / 2);
		this.setSize(widht, height);
		this.setPosition(X, Y);
	}
}
