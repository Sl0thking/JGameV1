package de.sloth.core.collisionHandle.behavior;

import de.sloth.core.collisionCheck.event.CollisionEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;

public class BDespawn implements IBehavior {

	@Override
	public void execute(DefaultGameSystem system) {}

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent) {
		CollisionEvent cevent = (CollisionEvent) expectedEvent;
		system.getEntityManager().removeEntity(cevent.getCollisionSrc());
		system.getEntityManager().removeEntity(cevent.getCollisionTarget());
	}
}