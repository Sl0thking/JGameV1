package de.sloth.event;

import de.sloth.entity.Entity;

public class BattleEvent extends AbstractEntitiesEvent {

	public BattleEvent(Entity collisionSrc, Entity collisionTarget) {
		super(collisionSrc, collisionTarget);
	}

}
