package de.sloth.system;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.LivingComp;
import de.sloth.entity.Entity;
import de.sloth.event.BattleEvent;
import de.sloth.event.CollisionEvent;
import de.sloth.event.GameEvent;

public class CollisionTestSystem extends GameSystem {

	public CollisionTestSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(entities, eventQueue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		while(true) {
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
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}