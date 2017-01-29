package de.sloth.systemv2.core;


public abstract interface IBehavior {
	public abstract void execute(GameSystem system);
	public abstract void execute(GameSystem system, GameEvent expectedEvent);
}
