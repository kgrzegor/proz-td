package com.mygdx.game.screens.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameButton extends Button
{
	private GameButton(final Builder builder)
	{
		super(builder.buttonStyle);

		init(builder.callback);

		this.setX(builder.X);
		this.setY(builder.Y);
		this.setWidth(builder.width);
		this.setHeight(builder.height);
		this.setDebug(builder.debug);
	}

	private void init(final IClickCallback callback)
	{
		

		this.addListener(new ClickListener()
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				callback.onClick();
				return super.touchDown(event, x, y, pointer, button);
			}
		});
	}
	
	/*********
	FLUENT BUILDER
	**********/
	
	public static class Builder
	{
		private int X, Y;
		private int width, height;
		private boolean debug;
		private final IClickCallback callback;
		private ButtonStyle buttonStyle;
		
		public Builder(final IClickCallback callback)
		{
			this.callback = callback;
			this.buttonStyle = new ButtonStyle();
		}
		
		public Builder position(int X, int Y)
		{
			this.X = X;
			this.Y = Y;
			return this;
		}
		
		public Builder width(int width)
		{
			this.width = width;
			return this;
		}
		
		public Builder height(int height)
		{
			this.height = height;
			return this;
		}
		
		public Builder debug(boolean debug)
		{
			this.debug = debug;
			return this;
		}
		public Builder image(String name)
		{
			Texture myTexture = new Texture(Gdx.files.internal(name));
			TextureRegion myTextureRegion = new TextureRegion(myTexture);
			TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
			
			this.buttonStyle.up = myTexRegionDrawable;
			this.buttonStyle.down = myTexRegionDrawable;
			
			return this;
		}
		
		public GameButton build()
		{
			return new GameButton(this);
		}
		
	}
}
