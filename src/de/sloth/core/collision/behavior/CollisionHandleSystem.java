package de.sloth.core.collision.behavior;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.sloth.core.collision.event.CollisionEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.component.Component;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;
import de.sloth.core.main.system.IEntityManagement;

public class CollisionHandleSystem extends GameSystem {
	private Map<String, IBehavior> collisionClassMapping; 
	
	
	public CollisionHandleSystem(String systemID, Class<? extends GameEvent> listeningEvent,
			IEntityManagement entityManager, List<GameEvent> eventQueue) {
		super(systemID, listeningEvent, entityManager, eventQueue);
		collisionClassMapping = new HashMap<String, IBehavior>();
	}
	
	public void registerCollisionBehavior(Class<? extends Component> movTypeComp, Class<? extends Component> collTypeComp, IBehavior behavior) {
		collisionClassMapping.put(movTypeComp.toString() + collTypeComp.toString(), behavior);
	}

	@Override
	public IBehavior getBehavior(GameEvent event) {
		CollisionEvent collEvent = (CollisionEvent) event;
		List<Class<?>> srcComps = collEvent.getCollisionSrc().getComponentClasses();
		List<Class<?>> destComps = collEvent.getCollisionTarget().getComponentClasses();
		for(Class<?> srcCompClass : srcComps) {
			for(Class<?> destCompClass : destComps) {
				if(collisionClassMapping.containsKey(srcCompClass.toString()+destCompClass.toString())) {
					return collisionClassMapping.get(srcCompClass.toString()+destCompClass.toString());
				}
			}
		}
		return null;
	}

	
	
	

}
