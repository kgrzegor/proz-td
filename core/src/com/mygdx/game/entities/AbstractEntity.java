package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
/**
 *	Entity with given texture file, on position (X,Y), with specified width and height
 */
public abstract class AbstractEntity extends Image
{
	public AbstractEntity(String texutreFile, int X, int Y, int width, int height)
	{
		super(new Texture(texutreFile));

		this.setOrigin(width / 2, height / 2);
		this.setSize(width, height);
		this.setPosition(X, Y);
	}
}
