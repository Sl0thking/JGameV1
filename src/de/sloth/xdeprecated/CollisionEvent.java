package de.sloth.xdeprecated;

import de.sloth.entity.Entity;

public class CollisionEvent extends AbstractEntitiesEvent {

	public CollisionEvent(Entity collisionSrc, Entity collisionTarget) {
		super(collisionSrc,collisionTarget);
	}

	@Override
	public String toString() {
		return "CollisionEvent [collisionSrc=" + this.getCollisionSrc() + ", collisionTarget=" + this.getCollisionTarget() + "]";
	}
	
	
}