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
	
	ProjectileInterface projectileInterface;

	public Projectile(ProjectileInterface projectileInterface, int towerRadius, float towerX, float towerY, float targetX, float targetY)
	{
		super(new Texture("projectile.png"));
		this.setPosition(towerX, towerY);
		this.projectileInterface = projectileInterface;
		init();
		//TODO both  direction shouldn't be sinus
		double distance = Math.hypot(targetX-towerX, targetY-towerY);
		directionX = (float)Math.sin((targetX-towerX)/distance) * towerRadius;
		directionY = (float)Math.sin((targetY-towerY)/distance) * towerRadius;
		
	}
	private void init()
	{
		this.setOrigin(WIDHT / 2, HEIGHT / 2);
		this.setSize(WIDHT, HEIGHT);
	}
	
	public void fire(float projectileSpeed)
	{
		Action a = Actions.parallel(Actions.moveBy(directionX, directionY, 1/projectileSpeed));
		Action c = Actions.run(new Runnable()
		{
			public void run()
			{
				projectileInterface.removeFromList(Projectile.this);
				Projectile.this.remove();
			}
		});
		
		this.addAction(Actions.sequence(a, c));
	}
	public void hit(Mob m)
	{
		System.out.println("HIT");
		projectileInterface.removeFromList(this);
		m.takeDamage();
		this.remove();
	}

}
