package de.sloth.core.controll.behavior;

import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;

public class BSendEvent implements IBehavior {

	private GameEvent sendingEvent;
	
	public BSendEvent(GameEvent event) {
		this.sendingEvent = event;
	}
	
	@Override
	public void execute(DefaultGameSystem system) {
		system.getEventQueue().add(sendingEvent);
	}

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent) {}
}