package com.mygdx.game.controllers;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.entities.PowerUp;
import com.mygdx.game.entities.enemies.EnemyFactory;
import com.mygdx.game.entities.enemies.Mob;
import com.mygdx.game.entities.enemies.MobType;
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
	private EnemyFactory enemyFactory;

	public MobController(Stage stage, PlayerLivesService playerLivesService, GoldService goldService,
			PointsService pointsService)
	{
		this.spawnTime = 3f;
		this.spawnCount = 15;
		this.stage = stage;
		this.playerLivesService = playerLivesService;
		this.goldService = goldService;
		this.pointsService = pointsService;
		this.mobsList = new ArrayList<Mob>();
		this.enemyFactory = new EnemyFactory(this);
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
		Mob newMob = enemyFactory.createMob(MobType.Yeti);
		stage.addActor(newMob);
		mobsList.add(newMob);
	}

	public void damagePlayer(Mob mob)
	{
		playerLivesService.makeDamage();
		removeFromStage(mob);
	}

	public void mobDied(Mob mob)
	{
		goldService.addGold(mob.getGold());
		pointsService.addPoints(mob.getPoints());
		removeFromStage(mob);

	}

	private void removeFromStage(Mob mob)
	{
		mobsList.remove(mob);
		mob.remove();
	}

	public void powerUpEffect(float time)
	{
		for (Mob m : mobsList)
			m.freeze(time / 10);
	}

	public ArrayList<Mob> getMobsList()
	{
		return mobsList;
	}

}
