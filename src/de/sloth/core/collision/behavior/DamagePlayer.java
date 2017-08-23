package de.sloth.core.collision.behavior;

import de.sloth.core.collision.event.CollisionEvent;
import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;
import de.sloth.core.main.component.AnimationComp;
import de.sloth.core.main.component.HealthComp;

public class DamagePlayer implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		CollisionEvent cevent = (CollisionEvent) expectedEvent;
		HealthComp hComp = (HealthComp) system.getEntityManager().getActivePlayabaleEntity().getComponent(HealthComp.class);
		AnimationComp aniComp = (AnimationComp) system.getEntityManager().getActivePlayabaleEntity().getComponent(AnimationComp.class);
		hComp.setLifes(hComp.getLifes()-1);
		aniComp.setAnimationPhase("hit");
		aniComp.setTicksForAnimation(8*15);
		system.getEntityManager().removeEntity(cevent.getCollisionSrc());
	}
}
