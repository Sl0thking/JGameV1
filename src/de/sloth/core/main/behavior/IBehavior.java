package de.sloth.core.main.behavior;

import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;



public abstract interface IBehavior {
	public abstract void execute(DefaultGameSystem system);
	public abstract void execute(DefaultGameSystem system, GameEvent expectedEvent) throws Exception;
}
