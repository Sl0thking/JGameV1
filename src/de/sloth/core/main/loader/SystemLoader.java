package de.sloth.core.main.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.factory.IBehaviorFactory;
import de.sloth.core.main.factory.ISystemFactory;
import de.sloth.core.main.system.GameSystem;
import de.sloth.core.main.system.IEntityManagement;

/**
 * Generator for specific game entities
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 17.05.2017
 *
 */
public abstract class SystemLoader {
	
	public static List<GameSystem> loadSystems(IEntityManagement entityManager, List<GameEvent> eventQueue) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser;
		List<GameSystem> systems = new LinkedList<GameSystem>();
		try {
			parser = factory.createXMLStreamReader(new FileInputStream("./xml/systems.xml"));
			GameSystem sys = null;
			while (parser.hasNext()) {
				if(parser.isStartElement() && parser.getLocalName().equals("system")) {
					
					
					HashMap<String, String> attrNameToValueMap = new HashMap<String, String>();
					ISystemFactory responsibleSysFactory = null;
					for(int i = 0; i < parser.getAttributeCount(); i++) {
						if(parser.getAttributeLocalName(i).equals("factory")) {
							responsibleSysFactory = (ISystemFactory) Class.forName(parser.getAttributeValue(i)).newInstance();
						} else {
							attrNameToValueMap.put(parser.getAttributeLocalName(i), parser.getAttributeValue(i));
						}
					}
					sys = responsibleSysFactory.createSystem(attrNameToValueMap, entityManager, eventQueue);
				}
				
				if(parser.isStartElement() && parser.getLocalName().equals("behavior")) {
					HashMap<String, String> bAttrNameToValueMap = new HashMap<String, String>();
					IBehaviorFactory bFactory = null;
					String keyword = "";
					
					for(int i = 0; i < parser.getAttributeCount(); i++) {
						if(parser.getAttributeLocalName(i).equals("factory")) {
							bFactory = (IBehaviorFactory) Class.forName(parser.getAttributeValue(i)).newInstance();
						} else if(parser.getAttributeLocalName(i).equals("eventKeyword")) {
							keyword = parser.getAttributeValue(i);
						} else {
							bAttrNameToValueMap.put(parser.getAttributeLocalName(i), parser.getAttributeValue(i));
						}
					}
					IBehavior behavior = bFactory.createBehavior(bAttrNameToValueMap);
					sys.registerBehavior(keyword, behavior);
				}
				
				if(parser.isEndElement() && parser.getLocalName().equals("system")) {
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