package de.sloth.core.main.behavior;

import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;



public abstract interface IBehavior {
	public abstract void execute(GameSystem system);
	public abstract void execute(GameSystem system, GameEvent expectedEvent) throws Exception;
}
