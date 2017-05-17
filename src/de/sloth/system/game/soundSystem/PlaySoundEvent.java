package de.sloth.system.game.soundSystem;

import de.sloth.system.game.core.GameEvent;

public class PlaySoundEvent extends GameEvent {
	private String desiredSoundFileName;

	public PlaySoundEvent(String desiredSoundFileName) {
		super();
		this.desiredSoundFileName = desiredSoundFileName;
	}
	
	public PlaySoundEvent(String keyword, String desiredSoundFileName) {
		super(keyword);
		this.desiredSoundFileName = desiredSoundFileName;
	}

	public String getDesiredSoundFileName() {
		return desiredSoundFileName;
	}

	public void setDesiredSoundFileName(String desiredSoundFileName) {
		this.desiredSoundFileName = desiredSoundFileName;
	}
}