package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.PowerUp;
import com.mygdx.game.entities.Mob;
import com.mygdx.game.entities.MobInterface;
import com.mygdx.game.services.GoldService;
import com.mygdx.game.services.PlayerLivesService;
import com.mygdx.game.services.PointsService;

public class MobController implements PowerUp
{
	private float spawnTime;
	private int spawnCount;
	private Stage stage;
	private PlayerLivesService playerLivesService;
	private ArrayList<Mob> mobsList;
	private GoldService goldService;
	private PointsService pointsService;

	public MobController(Stage stage, PlayerLivesService playerLivesService, GoldService goldService,
			PointsService pointsService)
	{
		spawnTime = 3f;
		spawnCount = 15;
		this.stage = stage;
		this.playerLivesService = playerLivesService;
		this.goldService = goldService;
		this.pointsService = pointsService;
		mobsList = new ArrayList<Mob>();
	}

	public void startWave(int id)
	{
		Timer.schedule(new Task()
		{
			public void run()
			{
				addMobToStage();
			}
		}, 0, spawnTime, spawnCount - 1);
	}

	private void addMobToStage()
	{
		Mob newMob = new Mob(new MobInterface()
		{
			@Override
			public void die(Mob mob)
			{
				goldService.addGold(mob.getGold()); // magic numbers
				pointsService.addPoints(mob.getPoints());
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

	public void popoutEffect(float time)
	{
		time /= 10;
		for (Mob m : mobsList)
			m.freeze(time);
	}
}
