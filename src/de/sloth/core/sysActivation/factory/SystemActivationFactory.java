package de.sloth.core.sysActivation.factory;

import java.util.HashMap;
import java.util.List;

import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.factory.IBehaviorFactory;
import de.sloth.core.main.factory.ISystemFactory;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.main.system.GameCore;
import de.sloth.core.main.system.IEntityManagement;
import de.sloth.core.sysActivation.system.SystemActivationSystem;

public class SystemActivationFactory implements ISystemFactory {

	@Override
	public DefaultGameSystem createSystem(
			HashMap<String, String> attrNameToValueMap,
			List<HashMap<String, String>> behaviorsFactoryData, GameCore core,
			IEntityManagement entityManager, List<GameEvent> eventQueue) {
		// TODO Auto-generated method stub
		SystemActivationSystem system = null;
		IBehaviorFactory responsibleBehaviorFactory = null;
		IBehavior behavior = null;
		try {
			String systemID = attrNameToValueMap.get("id");
			@SuppressWarnings("unchecked")
			Class<? extends GameEvent> eventClass = (Class<? extends GameEvent>) Class.forName(attrNameToValueMap.get("eventClass"));
			system = new SystemActivationSystem(systemID, eventClass, entityManager, eventQueue, core);
			
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
