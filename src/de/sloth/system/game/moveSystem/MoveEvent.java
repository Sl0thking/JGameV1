package de.sloth.system.game.moveSystem;

import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;

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