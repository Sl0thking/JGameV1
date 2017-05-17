package de.sloth.system.game.collision.commonBehavior;

import de.sloth.component.AnimationComp;
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
		AnimationComp aniComp = (AnimationComp) system.getEntityManager().getActivePlayabaleEntity().getComponent(AnimationComp.class);
		hComp.setLifes(hComp.getLifes()-1);
		aniComp.setAnimationPhase("hit");
		aniComp.setTicksForAnimation(8*15);
		system.getEntityManager().removeEntity(cevent.getCollisionSrc());
	}
}
