package de.sloth.core.collisionHandle.behavior;

import de.sloth.core.collisionCheck.event.CollisionEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.component.AnimationComp;
import de.sloth.core.main.component.HealthComp;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;

public class BDamagePlayer implements IBehavior {

	@Override
	public void execute(DefaultGameSystem system) {}

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent) {
		CollisionEvent cevent = (CollisionEvent) expectedEvent;
		HealthComp hComp = (HealthComp) system.getEntityManager().getActivePlayabaleEntity().getComponent(HealthComp.class);
		AnimationComp aniComp = (AnimationComp) system.getEntityManager().getActivePlayabaleEntity().getComponent(AnimationComp.class);
		hComp.setLifes(hComp.getLifes()-1);
		aniComp.setAnimationPhase("hit");
		aniComp.setTicksForAnimation(8*15);
		system.getEntityManager().removeEntity(cevent.getCollisionSrc());
	}
}
