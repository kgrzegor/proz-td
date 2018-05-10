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

	public MobController(Stage stage, PlayerLivesService playerLivesService2)
	{
		spawnTime = 3;
		spawnCount = 15;
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

		}, spawnTime / 10, spawnTime, spawnCount);
	}

	private void addMobToStage()
	{
		// should be changed to newMob as in projectileController
		mobsList.add(new Mob(new MobInterface()
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
		}));
		stage.addActor(mobsList.get(mobsList.size() - 1));
		mobsList.get(mobsList.size() - 1).followPath();

	}

	public ArrayList<Mob> getMobsList()
	{
		return mobsList;
	}
}
