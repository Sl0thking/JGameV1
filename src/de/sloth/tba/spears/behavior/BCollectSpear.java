package de.sloth.tba.spears.behavior;

import de.sloth.core.collisionCheck.event.CollisionEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.tba.spears.component.SpearBagComp;

/**
 * Behavior for a system which works with spears.
 * Is executed when a CollisionEvent between a spear 
 * and a entity who can carry spears (entitty contains SpearBagComp) 
 * occured. Removes spear from the game 
 * and increment spears in SpearBag.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 16.05.2017
 */
public class BCollectSpear implements IBehavior {

	@Override
	public void execute(DefaultGameSystem system) {}

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent) {
		CollisionEvent cevent = (CollisionEvent) expectedEvent;
		SpearBagComp spComp = (SpearBagComp) cevent.getCollisionSrc().getComponent(SpearBagComp.class);
		spComp.setSpears(spComp.getSpears()+1);
		system.getEntityManager().removeEntity(cevent.getCollisionTarget());
	}
}