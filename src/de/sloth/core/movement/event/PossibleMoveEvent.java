package de.sloth.core.movement.event;

import de.sloth.core.main.Entity;

public class PossibleMoveEvent extends MoveEvent{

	public PossibleMoveEvent(Direction direction) {
		super(direction);
	}
	
	public PossibleMoveEvent(Entity entity, Direction direction) {
		super(entity, direction);
	}
}
