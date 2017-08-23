package de.sloth.core.movement.event;

import de.sloth.core.main.Entity;
import de.sloth.core.main.GameEvent;

public class MoveEvent extends GameEvent {
	private Direction direction;
	private Entity entity;
	
	public MoveEvent(Entity entity, Direction direction) {
		this.direction = direction;
		this.setEntity(entity);
	}
	
	public MoveEvent(Direction direction) {
		this.direction = direction;
		this.setEntity(null);
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}