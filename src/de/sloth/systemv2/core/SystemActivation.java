package de.sloth.systemv2.core;

public class SystemActivation implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		SystemActivationEvent sae = (SystemActivationEvent) expectedEvent;
		if(sae.getTargetSystem().equals(this.getClass())) {
			system.setActive(sae.isActive());
			system.getEventQueue().remove(expectedEvent);
		}
	}
}
