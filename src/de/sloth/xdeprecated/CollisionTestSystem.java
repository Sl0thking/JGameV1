package de.sloth.xdeprecated;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.LivingComp;
import de.sloth.entity.Entity;
import de.sloth.system.GameSystem;
import de.sloth.xdeprecated.BattleEvent;
import de.sloth.xdeprecated.CollisionEvent;
import de.sloth.xdeprecated.GameEvent;

public class CollisionTestSystem extends GameSystem {

	public CollisionTestSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(entities, eventQueue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeSystem() {
		List<GameEvent> delEvents = new LinkedList<GameEvent>();
		for(GameEvent event:this.getEventQueue()) {
			if(event.getClass().equals(CollisionEvent.class)) {
				CollisionEvent cEvent = (CollisionEvent) event;
				System.out.println(cEvent);
				LivingComp type = (LivingComp) cEvent.getCollisionTarget().getComponent(LivingComp.class);
				if(type != null && type.isLiving()) {
					BattleEvent bEvent = new BattleEvent(cEvent.getCollisionSrc(), cEvent.getCollisionTarget());
					this.getEventQueue().add(bEvent);
				}
				delEvents.add(event);
			}
		}
		this.getEventQueue().removeAll(delEvents);
	}
}