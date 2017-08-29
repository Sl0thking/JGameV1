package de.sloth.core.collision.factory;

import java.util.HashMap;

import de.sloth.core.collision.component.HitboxComp;
import de.sloth.core.main.component.Component;
import de.sloth.core.main.factory.IComponentFactory;

public class HitboxFactory implements IComponentFactory {

	@Override
	public Component createComponent(HashMap<String, String> attrNameToValueMap)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		int width = Integer.parseInt(attrNameToValueMap.get("width"));
		int height = Integer.parseInt(attrNameToValueMap.get("height"));
		HitboxComp comp = new HitboxComp(width, height);
		return comp;
	}
}