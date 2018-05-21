package com.mygdx.game.screens.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class InfoLabel extends GameLabel
{
	public InfoLabel(Stage stage, int x, int y, String text)
	{
		super(stage, x, y);
		
		this.setText(text);
		
		moveUp();
	}

	private void moveUp()
	{
		Action move = Actions.moveBy(0, 150, 4);
		Action disappear = Actions.color(new Color(255, 255, 255, 0), 4);
		
		Action both = Actions.parallel(move, disappear);
		
		Action end = Actions.run(new Runnable()
		{
			public void run()
			{
				remove();
			}
		});
		
		this.addAction(Actions.sequence(both, end));
		
	}
}
