package com.mygdx.game.events;

import com.badlogic.gdx.scenes.scene2d.Event;

public class DamageEvent extends Event
{
	private final int damage;
	
    public DamageEvent(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
