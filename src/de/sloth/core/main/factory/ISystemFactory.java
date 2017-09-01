package de.sloth.core.main.factory;

import java.util.HashMap;
import java.util.List;

import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.main.system.GameCore;
import de.sloth.core.main.system.IEntityManagement;

public interface ISystemFactory {
	public DefaultGameSystem createSystem(HashMap<String, String> attrNameToValueMap,
			List<HashMap<String, String>> behaviorsFactoryData, GameCore core, IEntityManagement entityManager, List<GameEvent> eventQueue);
}
