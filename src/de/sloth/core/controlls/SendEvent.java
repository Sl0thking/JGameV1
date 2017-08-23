package de.sloth.core.controlls;

import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;

public class SendEvent implements IBehavior {

	private GameEvent sendingEvent;
	
	public SendEvent(GameEvent event) {
		this.sendingEvent = event;
	}
	
	@Override
	public void execute(GameSystem system) {
		system.getEventQueue().add(sendingEvent);
	}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {}
}