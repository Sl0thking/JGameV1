package de.sloth.core.main.factory;

import java.util.HashMap;
import java.util.List;

import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;
import de.sloth.core.main.system.IEntityManagement;

public interface ISystemFactory {
	public GameSystem createSystem(HashMap<String, String> attrNameToValueMap,
			IEntityManagement entityManager, List<GameEvent> eventQueue);
}
