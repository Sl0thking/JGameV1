package de.sloth.xdeprecated;

import de.sloth.xdeprecated.GameEvent;

public class SystemActivationEvent extends GameEvent {
	private boolean isActive;
	private Class<? extends GameSystem> targetSystem;
	
	public SystemActivationEvent(Class<? extends GameSystem> targetSystem) {
		this.isActive = true;
		this.targetSystem = targetSystem;
	}
	
	public SystemActivationEvent(Class<? extends GameSystem> targetSystem, boolean isActive) {
		this.isActive = isActive;
		this.targetSystem = targetSystem;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Class<? extends GameSystem> getTargetSystem() {
		return targetSystem;
	}

	public void setTargetSystem(Class<? extends GameSystem> targetSystem) {
		this.targetSystem = targetSystem;
	}
}
