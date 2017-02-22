package de.sloth.system.game.collision.commonBehavior;

import de.sloth.system.game.collision.CollisionEvent;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

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