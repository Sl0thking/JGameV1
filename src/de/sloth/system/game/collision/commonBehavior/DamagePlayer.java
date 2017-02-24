package de.sloth.system.game.collision.commonBehavior;

import de.sloth.component.HealthComp;
import de.sloth.system.game.collision.CollisionEvent;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class DamagePlayer implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		CollisionEvent cevent = (CollisionEvent) expectedEvent;
		HealthComp hComp = (HealthComp) system.getEntityManager().getActivePlayabaleEntity().getComponent(HealthComp.class);
		hComp.setLifes(hComp.getLifes()-1);
		system.getEntityManager().removeEntity(cevent.getCollisionSrc());
	}
}
