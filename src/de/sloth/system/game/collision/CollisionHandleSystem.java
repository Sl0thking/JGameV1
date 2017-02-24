package de.sloth.system.game.collision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.Component;
import de.sloth.system.game.core.ConfigLoader;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.game.core.IEntityManagement;

public class CollisionHandleSystem extends GameSystem {
	private Map<String, IBehavior> collisionClassMapping; 
	
	
	public CollisionHandleSystem(String systemID, Class<? extends GameEvent> listeningEvent,
			IEntityManagement entityManager, ConcurrentLinkedQueue<GameEvent> eventQueue) {
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
