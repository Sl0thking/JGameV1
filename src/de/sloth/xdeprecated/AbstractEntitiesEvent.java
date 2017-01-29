package de.sloth.xdeprecated;

import de.sloth.entity.Entity;

public abstract class AbstractEntitiesEvent extends GameEvent {
	private Entity collisionSrc;
	private Entity collisionTarget;
	

	public AbstractEntitiesEvent(Entity collisionSrc, Entity collisionTarget) {
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
