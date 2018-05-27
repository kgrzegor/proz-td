package com.mygdx.game.services;

/**
 * Provides adding points and saving highscore
 */
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
		this.points = 0;

		this.prefs = Gdx.app.getPreferences(GAME_PREFS);
		highscore = prefs.getInteger(GAME_HIGHSCORE);
	}

	private void updateHighscore()
	{
		if (points > highscore)
		{
			highscore = points;
			prefs.putInteger(GAME_HIGHSCORE, highscore);
			prefs.flush();
		}
	}
	
	public int getHighscore()
	{
		updateHighscore();

		return highscore;
	}

	public int getPoints()
	{
		return points;
	}

	public void addPoints(int points)
	{
		this.points += points;
	}



}
