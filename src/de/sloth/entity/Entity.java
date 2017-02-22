package de.sloth.entity;

import java.util.LinkedList;
import java.util.List;

import de.sloth.component.Component;

/**
 * Entity of this system
 * [SlothCore]
 * @author Slothking
 *
 */
public class Entity {
	@Override
	public String toString() {
		return "Entity [id=" + id + ", name=" + name + ", components=" + components + "]";
	}

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
	
	public List<Class<?>> getComponentClasses() {
		List<Class<?>> compClasses = new LinkedList<Class<?>>();
		for(Component comp : this.components) {
			compClasses.add(comp.getClass());
		}
		return compClasses;
	}
	
	public Component getComponent(Class<?> compClass) {
		for(Component comp : components) {
			if(comp.getClass().equals(compClass)) {
				return comp;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((components == null) ? 0 : components.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (components == null) {
			if (other.components != null)
				return false;
		} else if (!components.equals(other.components))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}