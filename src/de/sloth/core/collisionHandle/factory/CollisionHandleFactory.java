package de.sloth.core.collisionHandle.factory;

import java.util.HashMap;
import java.util.List;

import de.sloth.core.collisionHandle.system.CollisionHandleSystem;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.component.Component;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.factory.IBehaviorFactory;
import de.sloth.core.main.factory.ISystemFactory;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.main.system.GameCore;
import de.sloth.core.main.system.IEntityManagement;

public class CollisionHandleFactory implements ISystemFactory {

	@SuppressWarnings("unchecked")
	@Override
	public DefaultGameSystem createSystem(HashMap<String, String> attrNameToValueMap,
			List<HashMap<String, String>> behaviorsFactoryData, GameCore core,
			IEntityManagement entityManager, List<GameEvent> eventQueue) {
		CollisionHandleSystem system = null;
		IBehaviorFactory responsibleBehaviorFactory = null;
		IBehavior behavior = null;
		Class<? extends Component> colSrcCompClass = null;
		Class<? extends Component> colTargetCompClass = null;
		
		try {
			String systemID = attrNameToValueMap.get("id");
			Class<? extends GameEvent> eventClass = (Class<? extends GameEvent>) Class.forName(attrNameToValueMap.get("eventClass"));
			system = new CollisionHandleSystem(systemID, eventClass, entityManager, eventQueue);
			for(HashMap<String, String> behaviorFactoryData : behaviorsFactoryData) {
				if(behaviorFactoryData.get("colSrcCompClass") != "" && behaviorFactoryData.get("colTargetCompClass") != "") {
					responsibleBehaviorFactory = (IBehaviorFactory) Class.forName(behaviorFactoryData.get("factory")).newInstance();
					colSrcCompClass = (Class<? extends Component>) Class.forName(behaviorFactoryData.get("colSrcCompClass"));
					colTargetCompClass = (Class<? extends Component>) Class.forName(behaviorFactoryData.get("colTargetCompClass"));
					behavior = responsibleBehaviorFactory.createBehavior(behaviorFactoryData);
					system.registerCollisionBehavior(colSrcCompClass, colTargetCompClass, behavior);
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} 
 		return system;
	}
}