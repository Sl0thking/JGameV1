package de.sloth.core.main.factory;

import java.util.HashMap;

import de.sloth.core.main.behavior.IBehavior;

public class DefaultBehaviorFactory implements IBehaviorFactory {

	@Override
	public IBehavior createBehavior(HashMap<String, String> attrNameToValueMap) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		IBehavior behavior = (IBehavior) Class.forName(attrNameToValueMap.get("className")).newInstance();
		return behavior;
	}

}
