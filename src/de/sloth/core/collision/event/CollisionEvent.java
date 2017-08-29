package de.sloth.core.collision.event;

import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;

public class CollisionEvent extends GameEvent {
	private Entity collisionSrc;
	private Entity collisionTarget;
	

	public CollisionEvent(Entity collisionSrc, Entity collisionTarget) {
		this.collisionSrc = collisionSrc;
		this.collisionTarget = collisionTarget;
	}
	

	public Entity getCollisionSrc() {
		return collisionSrc;
	}

	public void setCollisionSrc(Entity collisionSrc) {
		this.collisionSrc = collisionSrc;
	}

	public Entity getCollisionTarget() {
		return collisionTarget;
	}

	public void setCollisionTarget(Entity collisionTarget) {
		this.collisionTarget = collisionTarget;
	}

}
