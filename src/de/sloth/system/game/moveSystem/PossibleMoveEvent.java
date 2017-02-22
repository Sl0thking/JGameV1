package de.sloth.system.game.moveSystem;

import de.sloth.entity.Entity;

public class PossibleMoveEvent extends MoveEvent{

	public PossibleMoveEvent(Direction direction) {
		super(direction);
	}
	
	public PossibleMoveEvent(Entity entity, Direction direction) {
		super(entity, direction);
	}
}
