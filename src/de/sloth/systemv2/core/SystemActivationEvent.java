package de.sloth.systemv2.core;

public class SystemActivationEvent extends GameEvent {
	private boolean isActive;
	private String targetSystemID;
	
	public SystemActivationEvent(String targetSystemID) {
		super();
		this.isActive = true;
		this.targetSystemID = targetSystemID;
	}
	
	public SystemActivationEvent(String targetSystemID, boolean isActive) {
		super();
		this.isActive = isActive;
		this.targetSystemID = targetSystemID;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getTargetSystem() {
		return targetSystemID;
	}

	public void setTargetSystem(String targetSystemID) {
		this.targetSystemID = targetSystemID;
	}
}
