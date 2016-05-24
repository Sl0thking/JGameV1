package de.sloth.entity;

import java.util.LinkedList;
import java.util.List;

import de.sloth.component.Component;

public class Entity {
	private int id;
	private String name;
	private List<Component> components;
	
	public Entity(int id, String name, List<Component> components) {
		super();
		this.id = id;
		this.name = name;
		this.components = components;
	}
	
	public Entity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.components = new LinkedList<Component>();
	}	
	
	public Entity() {
		super();
		this.id = 0;
		this.name = "Unknown Entity";
		this.components = new LinkedList<Component>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	public void addComponent(Component comp) {
		this.components.add(comp);
	}
	
	public List<Component> getComponents() {
		return this.components;
	}
	
	public List<Component> getComponents(Class<?> compClass) {
		List<Component> relatedComps = new LinkedList<Component>();
		for(Component comp : components) {
			if(comp.getClass().equals(compClass)) {
				relatedComps.add(comp);
			}
		}
		return relatedComps;
	}
}