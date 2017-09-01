package de.sloth.core.controll.event;

import de.sloth.core.collisionCheck.event.MoveEvent;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.movement.event.Direction;

public class PossibleMoveEvent extends MoveEvent{

	public PossibleMoveEvent(Direction direction) {
		super(direction);
	}
	
	public PossibleMoveEvent(Entity entity, Direction direction) {
		super(entity, direction);
	}
}
