package de.sloth.core.main.factory;

import java.util.HashMap;
import java.util.List;

import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.main.system.GameCore;
import de.sloth.core.main.system.IEntityManagement;

public class DefaultSystemFactory implements ISystemFactory {

	@SuppressWarnings("unchecked")
	@Override
	public DefaultGameSystem createSystem(HashMap<String, String> attrNameToValueMap,
			List<HashMap<String, String>> behaviorsFactoryData, GameCore core,
			IEntityManagement entityManager, List<GameEvent> eventQueue) {
		DefaultGameSystem system = null;
		IBehaviorFactory responsibleBehaviorFactory = null;
		IBehavior behavior = null;
		try {
			String systemID = attrNameToValueMap.get("id");
			Class<? extends GameEvent> eventClass = null;
			if(attrNameToValueMap.containsKey("eventClass") && attrNameToValueMap.get("eventClass") != "") {
				eventClass = (Class<? extends GameEvent>) Class.forName(attrNameToValueMap.get("eventClass"));
			}
			system = new DefaultGameSystem(systemID, eventClass, entityManager, eventQueue);
			
			for(HashMap<String, String> behaviorFactoryData : behaviorsFactoryData) {
				responsibleBehaviorFactory = (IBehaviorFactory) Class.forName(behaviorFactoryData.get("factory")).newInstance();
				behavior = responsibleBehaviorFactory.createBehavior(behaviorFactoryData);
				system.registerBehavior(behaviorFactoryData.get("eventKeyword"), behavior);
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} 
 		return system;
	}
}