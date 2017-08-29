package de.sloth.core.main.factory;

import java.util.HashMap;

import de.sloth.core.main.component.Component;

public interface IComponentFactory {
	public Component createComponent(HashMap<String, String> attrNameToValueMap) throws InstantiationException, IllegalAccessException, ClassNotFoundException;
}
