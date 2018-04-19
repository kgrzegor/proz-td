package com.mygdx.game.controllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Mob;
import com.mygdx.game.entities.MobInterface;
import com.mygdx.services.PlayerLivesService;

public class MobController
{
	private int spawnTime; //TODO: vectors for whole stage 
	private int spawnCount;
	private Stage stage;
	private PlayerLivesService playerLivesService;
	public MobController(Stage stage, PlayerLivesService playerLivesService2)
	{
		spawnTime = 3;
		spawnCount = 15;
		this.stage = stage; 
		this.playerLivesService = playerLivesService2;
	}
	
	public void startWave()
	{
		Timer.schedule(new Task()
		{
			public void run()
			{
				addMobToStage();
			}

			
		}, spawnTime, spawnTime, spawnCount);
	}
	private void addMobToStage()
	{
		Mob mob = new Mob(new MobInterface()
		{
			
			@Override
			public void takeDamage()
			{
				// TODO Auto-generated method stub
			}
			
			@Override
			public void makeDamage()
			{
				playerLivesService.makeDamage();				
			}
		});
		stage.addActor(mob);
		mob.followPath();
		
	}
}
