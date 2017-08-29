package de.sloth.core.main.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import de.sloth.core.main.component.Component;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.factory.IComponentFactory;

public class EntityLoader {
	
	public static Entity loadEntity(String name) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader parser;
		Entity entity = null;
		boolean isTargetEntity = false;
		try {
			parser = factory.createXMLStreamReader(new FileInputStream("./xml/predefinedEntities.xml"));
			while (parser.hasNext()) {
				if(parser.isStartElement() && parser.getLocalName().equals("entity")) {
					
					for(int i = 0; i < parser.getAttributeCount(); i++) {
						if(parser.getAttributeLocalName(i).equals("name") && parser.getAttributeValue(i).equals(name)) {
							isTargetEntity = true;
							entity = new Entity();
							entity.setName(name);
						}
					}
				}
				
				if(parser.isStartElement() && parser.getLocalName().equals("component") && isTargetEntity) {
					HashMap<String, String> bAttrNameToValueMap = new HashMap<String, String>();
					IComponentFactory cFactory = null;
					
					for(int i = 0; i < parser.getAttributeCount(); i++) {
						if(parser.getAttributeLocalName(i).equals("factory")) {
							cFactory = (IComponentFactory) Class.forName(parser.getAttributeValue(i)).newInstance();
						} else {
							bAttrNameToValueMap.put(parser.getAttributeLocalName(i), parser.getAttributeValue(i));
						}
					}
					Component comp = cFactory.createComponent(bAttrNameToValueMap);
					entity.addComponent(comp);
				}
				
				if(parser.isEndElement() && parser.getLocalName().equals("entity")) {
					isTargetEntity = false;
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
		return entity;
		
	}
}
