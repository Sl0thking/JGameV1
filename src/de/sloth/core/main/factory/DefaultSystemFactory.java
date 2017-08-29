package de.sloth.core.main.factory;

import java.util.HashMap;
import java.util.List;

import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;
import de.sloth.core.main.system.IEntityManagement;

public class DefaultSystemFactory implements ISystemFactory {

	@Override
	public GameSystem createSystem(HashMap<String, String> attrNameToValueMap, IEntityManagement entityManager, List<GameEvent> eventQueue) {
		GameSystem system = null;
		try {
			String systemID = attrNameToValueMap.get("id");
			@SuppressWarnings("unchecked")
			Class<? extends GameEvent> eventClass = (Class<? extends GameEvent>) Class.forName(attrNameToValueMap.get("eventClass"));
			system = new GameSystem(systemID, eventClass, entityManager, eventQueue);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 		return system;
	}

}
