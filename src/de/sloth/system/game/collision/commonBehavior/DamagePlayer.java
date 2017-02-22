package de.sloth.system.game.collision.commonBehavior;

import de.sloth.system.game.collision.CollisionEvent;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.testgame.main.SlothComp;

public class DamagePlayer implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		CollisionEvent cevent = (CollisionEvent) expectedEvent;
		SlothComp scomp = (SlothComp) system.getEntityManager().getActivePlayabaleEntity().getComponent(SlothComp.class);
		scomp.setLifes(scomp.getLifes()-1);
		if(scomp.getLifes() <= 0) {
			System.exit(0);
		}
		system.getEntityManager().removeEntity(cevent.getCollisionSrc());
		
	}
}
