package de.sloth.systemv2.collision;

import java.util.LinkedList;
import java.util.List;

import de.sloth.component.LivingComp;
import de.sloth.systemv2.battle.BattleEvent;
import de.sloth.systemv2.core.GameEvent;
import de.sloth.systemv2.core.GameSystem;
import de.sloth.systemv2.core.IBehavior;

public class Collision implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		List<GameEvent> delEvents = new LinkedList<GameEvent>();
		for(GameEvent event:system.getEventQueue()) {
			if(event.getClass().equals(CollisionEvent.class)) {
				CollisionEvent cEvent = (CollisionEvent) event;
				System.out.println(cEvent);
				LivingComp type = (LivingComp) cEvent.getCollisionTarget().getComponent(LivingComp.class);
				if(type != null && type.isLiving()) {
					BattleEvent bEvent = new BattleEvent(cEvent.getCollisionSrc(), cEvent.getCollisionTarget());
					system.getEventQueue().add(bEvent);
				}
				delEvents.add(event);
			}
		}
		system.getEventQueue().removeAll(delEvents);
	}
}