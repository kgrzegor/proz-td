package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.Mob;
import com.mygdx.game.entities.MobInterface;
import com.mygdx.game.services.GoldService;
import com.mygdx.game.services.PlayerLivesService;

public class MobController
{
	private float spawnTime; // TODO: vectors for whole stage
	private int spawnCount;
	private Stage stage;
	private PlayerLivesService playerLivesService;
	private ArrayList<Mob> mobsList;
	private Mob newMob;
	private GoldService goldService;

	public MobController(Stage stage, PlayerLivesService playerLivesService2, GoldService goldService)
	{
		spawnTime = 3f;
		spawnCount = 15;
		this.stage = stage;
		this.playerLivesService = playerLivesService2;
		this.goldService = goldService;
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

		}, spawnTime / 10, spawnTime, spawnCount - 1);
	}

	private void addMobToStage()
	{
		newMob = new Mob(new MobInterface()
		{

			@Override
			public void die(Mob mob)
			{
				goldService.addGold(50); // magic numbers
			}

			@Override
			public void makeDamage()
			{
				playerLivesService.makeDamage();
			}

			@Override
			public void removeFromStage(Mob mob)
			{
				mobsList.remove(mob);
				mob.remove();
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
