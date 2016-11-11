package de.sloth.event;

import de.sloth.entity.Entity;

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

	@Override
	public String toString() {
		return "CollisionEvent [collisionSrc=" + collisionSrc + ", collisionTarget=" + collisionTarget + "]";
	}
	
	
}