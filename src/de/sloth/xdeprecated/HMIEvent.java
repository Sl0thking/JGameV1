package de.sloth.xdeprecated;

public class HMIEvent extends GameEvent {
	public HMIKeyword hmiKeyword;
	
	public HMIEvent(HMIKeyword keyword) {
		this.hmiKeyword = keyword;
	}

	public HMIKeyword getHmiKeyword() {
		return hmiKeyword;
	}

	public void setHmiKeyword(HMIKeyword hmiKeyword) {
		this.hmiKeyword = hmiKeyword;
	}
	
	
}
