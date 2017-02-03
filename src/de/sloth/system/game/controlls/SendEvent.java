package de.sloth.system.game.controlls;

import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

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