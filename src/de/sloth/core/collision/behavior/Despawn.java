package de.sloth.core.collision.behavior;

import de.sloth.core.collision.event.CollisionEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;

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