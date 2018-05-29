package com.mygdx.game.controllers;

import java.util.LinkedList;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.PowerupAffected;
import com.mygdx.game.events.DamageEvent;
import com.mygdx.game.events.FreezeEvent;
import com.mygdx.game.screens.ui.GameButton;
import com.mygdx.game.screens.ui.IClickCallback;
import com.mygdx.game.screens.ui.InfoLabel;
import com.mygdx.game.services.StageService;

/**
 * Spawn powerups in different place on map each time with different effect
 */
public class PowerupController
{
	private final static int respawnTime = 5;
	private LinkedList <PowerupAffected> affected;
	private boolean started;
	private Random rand;
	
	
	private StageService stageService;
	private GameButton powerup;
	private Stage stage;

	public PowerupController(Stage stage, MyGdxGame game)
	{
		this.affected = new LinkedList<PowerupAffected>();
		this.rand = new Random();
		this.started = false;		
		
		this.stage = stage;
		this.stageService = game.getStageService();
		
	}

	/**
	 * Every respawnTime seconds new powerUp appears in random place on map
	 */
	public void startPowerUps()
	{
		if (!started)
			Timer.schedule(new Task()
			{
				public void run()
				{
					newPowerup();
					stage.addActor(powerup);
					initRemoveTimer();
				}
			}, respawnTime, respawnTime);

		started = true;
	}

	/**
	 * After some time powerup disappears
	 */
	private void initRemoveTimer()
	{
		Timer.schedule(new Task()
		{
			public void run()
			{
				powerup.remove();
			}
		}, 3);
	}

	/**
	 * Choose random position for power up, needs to be added to stage. Position
	 * will be allways choosen at least 100px from map edge
	 */
	private void newPowerup()
	{
		final int X = rand.nextInt(MyGdxGame.WIDTH - 200) + 100;
		final int Y = rand.nextInt(MyGdxGame.HEIGHT - 200) + 100;

		powerup = new GameButton.Builder(new IClickCallback()
		{
			@Override
			public void onClick()
			{
				clicked(X, Y);

			}
		}).position(X, Y).height(25).width(28).image("map/powerup.png").build();
	}

	/**
	 * After being clicked one of the powerup effects is chosen, infolabel with
	 * effect is shown and powerup is removed.
	 */
	protected void clicked(int x, int y)
	{
		String powerupInfo = null;
		Event powerupEffect = null;
		
		switch (rand.nextInt(2))
		{
		case 0:
			powerupInfo = "Towers have bonus damage!\nMobs have been damaged!";
			powerupEffect = new DamageEvent(stageService.getCurrentStage()*10);
			break;
		case 1:
			powerupInfo = "Mobs have been frozen!";
			powerupEffect = new FreezeEvent(stageService.getCurrentStage());
			break;
		default:
			throw new IllegalArgumentException("Wrong powerup Effect");
		}
		
		for (PowerupAffected a : affected)
			a.fire(powerupEffect);
		
		new InfoLabel(stage, x, y, powerupInfo);
		powerup.remove();
	}

	public void addAffected(PowerupAffected p)
	{
		affected.add(p);
	}
	public void removeAffected(PowerupAffected p)
	{
		affected.remove(p);
	}
}
