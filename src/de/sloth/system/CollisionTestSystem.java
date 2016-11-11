package de.sloth.system;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
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
					this.getEntities().remove(cEvent.getCollisionTarget());
					System.out.println(event);
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