package de.sloth.core.main.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.factory.IBehaviorFactory;
import de.sloth.core.main.factory.ISystemFactory;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.main.system.GameCore;
import de.sloth.core.main.system.IEntityManagement;

/**
 * Generator for specific game entities
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 17.05.2017
 *
 */
public abstract class SystemLoader {
	
	public static List<DefaultGameSystem> loadSystems(GameCore core, IEntityManagement entityManager, List<GameEvent> eventQueue) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser;
		List<DefaultGameSystem> systems = new LinkedList<DefaultGameSystem>();
		try {
			parser = factory.createXMLStreamReader(new FileInputStream("./xml/systems.xml"));
			DefaultGameSystem sys = null;
			HashMap<String, String> systemFactoryData = new HashMap<String, String>();
			List<HashMap<String, String>> behaviorsFactoryData = new LinkedList<HashMap<String, String>>();
			ISystemFactory responsibleSysFactory = null;
			while (parser.hasNext()) {
				if(parser.isStartElement() && parser.getLocalName().equals("system")) {
					systemFactoryData.clear();
					behaviorsFactoryData.clear();
					for(int i = 0; i < parser.getAttributeCount(); i++) {
						if(parser.getAttributeLocalName(i).equals("factory")) {
							responsibleSysFactory = (ISystemFactory) Class.forName(parser.getAttributeValue(i)).newInstance();
						} else {
							systemFactoryData.put(parser.getAttributeLocalName(i), parser.getAttributeValue(i));
						}
					}
				}
				
				if(parser.isStartElement() && parser.getLocalName().equals("behavior")) {
					HashMap<String, String> bAttrNameToValueMap = new HashMap<String, String>();
					
					for(int i = 0; i < parser.getAttributeCount(); i++) {
						bAttrNameToValueMap.put(parser.getAttributeLocalName(i), parser.getAttributeValue(i));
					}
					behaviorsFactoryData.add(bAttrNameToValueMap);
				}
				
				if(parser.isEndElement() && parser.getLocalName().equals("system")) {
					sys = responsibleSysFactory.createSystem(systemFactoryData, behaviorsFactoryData, core, entityManager, eventQueue);
					systems.add(sys);
				}
				parser.next();
			}
		} catch (FileNotFoundException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return systems;
	}
}