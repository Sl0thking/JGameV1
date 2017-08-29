package de.sloth.core.main.factory;

import java.util.HashMap;

import de.sloth.core.main.behavior.IBehavior;

public interface IBehaviorFactory {
	public IBehavior createBehavior(HashMap<String, String> attrNameToValueMap) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
}
