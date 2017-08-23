package de.sloth.core.collision.behavior;

import de.sloth.core.collision.event.CollisionEvent;
import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;

public class Despawn implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		CollisionEvent cevent = (CollisionEvent) expectedEvent;
		system.getEntityManager().removeEntity(cevent.getCollisionSrc());
		system.getEntityManager().removeEntity(cevent.getCollisionTarget());
	}
}