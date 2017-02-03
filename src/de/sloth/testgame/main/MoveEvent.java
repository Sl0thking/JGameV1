package de.sloth.testgame.main;

import de.sloth.system.game.core.GameEvent;

public class MoveEvent extends GameEvent {
	private Direction direction;
	
	public MoveEvent(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}