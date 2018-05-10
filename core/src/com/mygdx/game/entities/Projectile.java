package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Projectile extends Image
{
	private final static int WIDHT = 10;
	private final static int HEIGHT = 10;
	
	
	float directionX;
	float directionY;
	private float aSlope;
	
	public Projectile(int towerRadius, float originX, float originY, float targetX, float targetY)
	{
		super(new Texture("projectile.png"));
		init(originX, originY);
		
		if ( Math.abs(targetX - originX) > 0.01)
		{
			this.aSlope = (targetY - originY) / (targetX - originX);
			
			if (Math.abs(aSlope) > 0.01)
			{
				calulateDirections(towerRadius);				
			}
			else
			{
				directionY = 0;
				directionX = targetX - originX > 0 ? towerRadius : -towerRadius;
			}
		}
		else
		{
			directionX = 0;
			directionY = targetY - originY > 0 ? towerRadius : -towerRadius;
		}
	}
	private void init(float originX, float originY)
	{
		this.setOrigin(WIDHT / 2, HEIGHT / 2);
		this.setSize(WIDHT, HEIGHT);
		this.setPosition(originX, originY);
	}
	private void calulateDirections(int towerRadius)
	{
		double alpha = Math.atan(aSlope);
		directionX = (float) (Math.sin(alpha) * towerRadius);
		directionX = (float) (Math.cos(alpha) * towerRadius);		
	}
	
	public void fire(float projectileSpeed)
	{
		Action a = Actions.parallel(Actions.moveBy(directionX, directionY, 1/projectileSpeed));
		Action c = Actions.run(new Runnable()
		{
			public void run()
			{
				Projectile.this.remove();
			}
		});
		
		this.addAction(Actions.sequence(a, c));
	}


}
