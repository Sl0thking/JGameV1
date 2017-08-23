package de.sloth.core.main;


public abstract interface IBehavior {
	public abstract void execute(GameSystem system);
	public abstract void execute(GameSystem system, GameEvent expectedEvent);
}
