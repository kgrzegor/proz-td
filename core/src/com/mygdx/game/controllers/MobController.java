package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Mob;
import com.mygdx.game.entities.MobInterface;
import com.mygdx.game.services.PlayerLivesService;

public class MobController
{
	private int spawnTime; // TODO: vectors for whole stage
	private int spawnCount;
	private Stage stage;
	private PlayerLivesService playerLivesService;
	private ArrayList<Mob> mobsList;
	private Mob newMob;

	public MobController(Stage stage, PlayerLivesService playerLivesService2)
	{
		spawnTime = 3;
		spawnCount = 1;
		this.stage = stage;
		this.playerLivesService = playerLivesService2;
		mobsList = new ArrayList<Mob>();

	}

	public void startWave()
	{
		Timer.schedule(new Task()
		{
			public void run()
			{
				addMobToStage();
			}

		}, spawnTime / 10, spawnTime, spawnCount-1);
	}

	private void addMobToStage()
	{
		// should be changed to newMob as in projectileController
		newMob = new Mob(new MobInterface()
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
		stage.addActor(newMob);
		newMob.followPath();
		mobsList.add(newMob);

	}

	public ArrayList<Mob> getMobsList()
	{
		return mobsList;
	}
}
