package com.mygdx.game.controllers;

import java.util.LinkedList;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.PowerUp;
import com.mygdx.game.entities.enemies.EnemyFactory;
import com.mygdx.game.entities.enemies.Mob;
import com.mygdx.game.services.GoldService;
import com.mygdx.game.services.PlayerLivesService;
import com.mygdx.game.services.PointsService;
import com.mygdx.game.services.StageService;
import com.mygdx.game.services.TimeService;

/**
 * Spawn Mobs according to stage service data and put them on stage.
 **/
public class MobController implements PowerUp
{
	private LinkedList<Mob> mobsList;
	private int mobsCreated;// used only in last stage
	private Stage stage;
	private EnemyFactory enemyFactory;

	private PlayerLivesService playerLivesService;
	private GoldService goldService;
	private PointsService pointsService;
	private StageService stageService;
	private TimeService timeService;

	public MobController(Stage stage, MyGdxGame game)
	{
		this.mobsList = new LinkedList<Mob>();
		this.stage = stage;
		this.mobsCreated = 0; // used only in last stage
		this.enemyFactory = new EnemyFactory(this);

		this.playerLivesService = game.getPlayerLivesService();
		this.goldService = game.getGoldService();
		this.pointsService = game.getPointsService();
		this.stageService = game.getStageService();
		this.timeService = game.getTimeService();
	}

	/**
	 * Starts timer in first stage, resets it in every new wave, stops in last wave.
	 * Spawn mob type with time between and number of them in wave according to
	 * stageService. Increment stage counter.
	 **/
	public void startWave()
	{

		if (stageService.getCurrentStage() == 0)
			timeService.start();

		if (stageService.hasNextStage())
		{
			stageService.nextStage();
			timeService.resetTime();

			final int waveNumber = stageService.getCurrentStage();
			Timer.schedule(new Task()
			{
				public void run()
				{
					addMobToStage(waveNumber);
				}
			}, 0, stageService.getSpawnTime(waveNumber), stageService.getSpawnCount() - 1);

		} else
		{
			timeService.setStopped(true);
		}
	}

	/**
	 * Uses factory to spawn different mob type
	 **/
	private void addMobToStage(int waveNumber)
	{
		Mob newMob = enemyFactory.createMob(stageService.getWaveType(waveNumber));
		stage.addActor(newMob);
		mobsList.add(newMob);

		if (waveNumber == stageService.getLastStage())
			++mobsCreated;
	}

	/**
	 * Every mob makes one damage to player, with more mob types this could be
	 * changed
	 **/
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

	/**
	 * Checks if player killed all enemies in all stages so game can be ended
	 **/
	public boolean allEnemyKilled()
	{
		if (mobsList.isEmpty() && mobsCreated == stageService.getSpawnCountLastStage())
			return true;
		else
			return false;
	}

	public String powerUpEffect(float time)
	{
		for (Mob m : mobsList)
			m.freeze(time / 10);
		return "Mobs have been frozen";
	}

	public LinkedList<Mob> getMobsList()
	{
		return mobsList;
	}
}
