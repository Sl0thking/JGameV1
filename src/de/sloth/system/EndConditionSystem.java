package de.sloth.system;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.FocusComp;
import de.sloth.component.LivingComp;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.event.HMIEvent;
import de.sloth.event.HMIKeyword;

public class EndConditionSystem extends GameSystem {

	public EndConditionSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(entities, eventQueue);
		// TODO Auto-generated constructor stub
	}

	public EndConditionSystem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeSystem() {
		List<Entity> delEntities = new LinkedList<Entity>();
		
		for(Entity entity : this.getEntities()) {
			FocusComp fComp = (FocusComp) entity.getComponent(FocusComp.class);
			LivingComp lComp = (LivingComp) entity.getComponent(LivingComp.class);
			if(fComp != null && lComp != null) {
				if(lComp.getHp() <= 0) {
					delEntities.add(entity);
					HMIEvent event = new HMIEvent(HMIKeyword.toggleGameOver);
					this.getEventQueue().add(event);
				}
			}
		}
		this.getEntities().removeAll(delEntities);
		
	}
}