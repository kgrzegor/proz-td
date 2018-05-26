package com.mygdx.game.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PointsService
{
	public final static String GAME_PREFS = "com.mygdx.game.prefs";
	public final static String GAME_HIGHSCORE = "com.mygdx.game.prefs.highscore";
	
	private Preferences prefs;
	private int points;
	private int highscore;

	public PointsService()
	{
		points = 0;
		
		prefs =  Gdx.app.getPreferences(GAME_PREFS);
		loadHighscore();
	}

	private void loadHighscore()
	{
		highscore = prefs.getInteger(GAME_HIGHSCORE);
	}

	public int getPoints()
	{
		return points;
	}

	public void addPoints(int points)
	{
		this.points += points;
	}

	public int getHighscore()
	{
		if (points >= highscore)
		{
			highscore = points;
			prefs.putInteger(GAME_HIGHSCORE, highscore);
		}
		
		return highscore;
	}

	
	
	
}
