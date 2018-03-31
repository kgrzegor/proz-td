package com.mygdx.game.controllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Mob;

public class MobController
{
	private int spawnTime; //TODO: vectors for whole stage 
	private int spawnCount;
	private Stage stage;
	
	public MobController(Stage stage)
	{
		spawnTime = 3;
		spawnCount = 15;
		this.stage = stage; 
	}
	
	public void startWave()
	{
		Timer.schedule(new Task()
		{
			public void run()
			{
				addMobToStage(stage);
			}

			
		}, spawnTime, spawnTime, spawnCount);
	}
	private void addMobToStage(Stage stage2)
	{
		Mob mob = new Mob();
		
		stage.addActor(mob);
		mob.followPath();
		
	}
	
}
