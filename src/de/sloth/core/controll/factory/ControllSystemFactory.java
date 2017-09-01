package de.sloth.core.controll.factory;

import java.util.HashMap;
import java.util.List;

import javafx.scene.input.KeyCode;
import de.sloth.core.controll.system.ControllSystem;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.factory.IBehaviorFactory;
import de.sloth.core.main.factory.ISystemFactory;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.main.system.GameCore;
import de.sloth.core.main.system.IEntityManagement;

public class ControllSystemFactory implements ISystemFactory {

	@Override
	public DefaultGameSystem createSystem(
			HashMap<String, String> attrNameToValueMap,
			List<HashMap<String, String>> behaviorsFactoryData, GameCore core,
			IEntityManagement entityManager, List<GameEvent> eventQueue) {
		ControllSystem system = null;
		IBehaviorFactory responsibleBehaviorFactory;
		IBehavior behavior = null;
		KeyCode bindingKey = null;
		try {
			String systemID = attrNameToValueMap.get("id");
			system = new ControllSystem(systemID, entityManager, eventQueue);
			for(HashMap<String, String> behaviorFactoryData : behaviorsFactoryData) {
				if(behaviorFactoryData.get("keyCodeName") != null) {
					responsibleBehaviorFactory = (IBehaviorFactory) Class.forName(behaviorFactoryData.get("factory")).newInstance();
					bindingKey = KeyCode.getKeyCode(behaviorFactoryData.get("keyCodeName"));
					behavior = responsibleBehaviorFactory.createBehavior(behaviorFactoryData);
					system.registerKeyToBehavior(bindingKey, behavior);
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} 
 		return system;
	}

}
