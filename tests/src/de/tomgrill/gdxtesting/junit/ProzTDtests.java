/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package de.tomgrill.gdxtesting.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.exceptions.GoldException;
import com.mygdx.game.exceptions.UpgradeException;
import com.mygdx.game.services.GoldService;
import com.mygdx.game.services.PlayerLivesService;
import com.mygdx.game.services.PointsService;
import com.mygdx.game.services.StageService;
import com.mygdx.game.services.TimeService;
import com.mygdx.game.services.UpgradeService;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class ProzTDtests
{

	/*
	 * Assets tests
	 */
	@Test
	public void mapFileExists()
	{
		assertTrue(Gdx.files.internal("../core/assets/map/map.jpg").exists());
	}

	@Test
	public void fieldFileExists()
	{
		assertTrue(Gdx.files.internal("../core/assets/map/field.png").exists());
	}

	@Test
	public void powerupFileExists()
	{
		assertTrue(Gdx.files.internal("../core/assets/map/powerup.png").exists());
	}

	@Test
	public void projectileFileExists()
	{
		assertTrue(Gdx.files.internal("../core/assets/map/projectile.png").exists());
	}

	@Test
	public void towerFileExists()
	{
		assertTrue(Gdx.files.internal("../core/assets/map/tower.png").exists());
	}

	@Test
	public void menuFileExists()
	{
		assertTrue(Gdx.files.internal("../core/assets/menu/menu.png").exists());
	}

	@Test
	public void gameoverFileExists()
	{
		assertTrue(Gdx.files.internal("../core/assets/menu/gameover.png").exists());
	}

	@Test
	public void youwonFileExists()
	{
		assertTrue(Gdx.files.internal("../core/assets/menu/youwon.png").exists());
	}

	@Test
	public void startFileExists()
	{
		assertTrue(Gdx.files.internal("../core/assets/menu/start.png").exists());
	}

	@Test
	public void exitFileExists()
	{
		assertTrue(Gdx.files.internal("../core/assets/menu/exit.png").exists());
	}

	/*
	 * Services
	 */
	@Test
	public void goldServiceConstructor()
	{
		GoldService test = new GoldService(0);

		assertEquals(0, test.getGold());
	}

	@Test
	public void goldServiceAddGold()
	{
		GoldService test = new GoldService(0);
		test.addGold(500);
		assertEquals(500, test.getGold());
	}

	@Test
	public void goldServiceSpendGold1()
	{
		GoldService test = new GoldService(500);

		try
		{
			test.spendGold(300);
		} catch (GoldException e)
		{
			assertTrue(false);
		}

		assertEquals(200, test.getGold());
	}

	@Test
	public void goldServiceSpendGold2()
	{
		GoldService test = new GoldService(500);
		try
		{
			test.spendGold(600);
			assertTrue(false);
		} catch (GoldException e)
		{
			assertEquals("Not enough gold!\nYou need 100g more.", e.getMessage());
		}
	}

	@Test
	public void playerLivesServiceConstructor()
	{
		PlayerLivesService test = new PlayerLivesService(0);
		assertTrue(test.gameOver());
	}

	@Test
	public void playerLivesServiceConstructor2()
	{
		PlayerLivesService test = new PlayerLivesService(100);
		assertEquals(test.getLivesLeft(), test.getStartingLives());
		assertFalse(test.gameOver());
	}

	@Test
	public void playerLivesServiceConstructor3()
	{
		PlayerLivesService test = new PlayerLivesService(100);
		assertFalse(test.gameOver());
	}

	@Test
	public void playerLivesServiceMakeDamage()
	{
		PlayerLivesService test = new PlayerLivesService(100);
		test.makeDamage();
		assertTrue(test.getLivesLeft() != test.getStartingLives());
	}

	@Test
	public void playerLivesServiceMakeDamage2()
	{
		PlayerLivesService test = new PlayerLivesService(100);
		test.makeDamage();
		assertEquals(99, test.getLivesLeft());
	}

	@Test
	public void playerLivesServiceMakeDamage3()
	{
		PlayerLivesService test = new PlayerLivesService(5);
		for (int i = 0; i < 10; ++i)
			test.makeDamage();
		assertEquals(0, test.getLivesLeft());
	}

	@Test
	public void playerLivesServiceMakeDamage4()
	{
		PlayerLivesService test = new PlayerLivesService(5);
		for (int i = 0; i < 10; ++i)
			test.makeDamage();
		assertTrue(test.gameOver());
	}

	@Test
	public void pointsServiceConstructor()
	{
		PointsService test = new PointsService();
		assertEquals(0, test.getPoints());
	}

	@Test
	public void pointsServiceAddPoints()
	{
		PointsService test = new PointsService();
		test.addPoints(100);
		assertEquals(100, test.getPoints());
	}

	@Test
	public void stageServiceConstructor1()
	{
		StageService test = new StageService();
		assertEquals(0, test.getCurrentStage());
	}

	@Test
	public void stageServiceConstructor2()
	{
		StageService test = new StageService();
		assertNotEquals(0, test.getLastStage());
	}

	@Test
	public void stageServiceNextStage()
	{
		StageService test = new StageService();
		test.nextStage();
		assertEquals(1, test.getCurrentStage());
	}

	@Test
	public void stageServiceNextStage2()
	{
		StageService test = new StageService();
		for (int i = 0; i < 1000; ++i)
			test.nextStage();
		assertTrue(test.getCurrentStage() == test.getLastStage());
	}

	@Test
	public void stageServiceHasNextStage1()
	{
		StageService test = new StageService();
		for (int i = 0; i < 1000; ++i)
			test.nextStage();
		assertFalse(test.hasNextStage());
	}

	@Test
	public void stageServiceHasNextStage2()
	{
		StageService test = new StageService();
		assertTrue(test.hasNextStage());
	}

	@Test
	public void upgradeServiceGetDamageCost()
	{
		UpgradeService test = new UpgradeService();
		int d1 = test.getDamageCost();
		try
		{
			test.nextDamageLvl();
		} catch (UpgradeException e)
		{
			assertTrue(false);
		}
		int d2 = test.getDamageCost();
		assertTrue(d1 < d2);
	}

	@Test
	public void upgradeServiceNextDamageLvl()
	{
		UpgradeService test = new UpgradeService();
		try
		{
			for (int i = 0; i < 1000; ++i)
				test.nextDamageLvl();
			assertTrue(false);
		} catch (UpgradeException e)
		{
			assertEquals("Tower damage at max level!", e.getMessage());
		}
	}

	@Test
	public void upgradeServiceGetRangeCost()
	{
		UpgradeService test = new UpgradeService();
		int r1 = test.getRangeCost();
		try
		{
			test.nextRangeLvl();
		} catch (UpgradeException e)
		{
			assertTrue(false);
		}
		int r2 = test.getRangeCost();
		assertTrue(r1 < r2);
	}

	@Test
	public void upgradeServiceNextRangeLvl()
	{
		UpgradeService test = new UpgradeService();
		try
		{
			for (int i = 0; i < 1000; ++i)
				test.nextRangeLvl();
			assertTrue(false);
		} catch (UpgradeException e)
		{
			assertEquals("Tower range at max level!", e.getMessage());
		}
	}

	@Test
	public void upgradeServiceGetFireRateCooldownCost()
	{
		UpgradeService test = new UpgradeService();
		int f1 = test.getFireRateCooldownCost();
		try
		{
			test.nextfireRateCooldownLvl();
		} catch (UpgradeException e)
		{
			assertTrue(false);
		}
		int f2 = test.getFireRateCooldownCost();
		assertTrue(f1 < f2);
	}

	@Test
	public void upgradeServiceNextfireRateCooldownLvl()
	{
		UpgradeService test = new UpgradeService();
		try
		{
			for (int i = 0; i < 1000; ++i)
				test.nextfireRateCooldownLvl();
			assertTrue(false);
		} catch (UpgradeException e)
		{
			assertEquals("Tower fire rate at max level!", e.getMessage());
		}
	}

	@Test
	public void timeServiceConstructor()
	{
		TimeService test = new TimeService();
		assertEquals(60, test.getTime());
	}

	@Test
	public void timeServiceRestTime()
	{
		TimeService test = new TimeService();
		int pre = test.getTime();
		test.resetTime();
		int post = test.getTime();
		assertTrue(pre == post);
	}
}
