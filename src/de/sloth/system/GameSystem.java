package de.sloth.system;

import java.util.LinkedList;
import java.util.List;

import de.sloth.entity.Entity;

public abstract class GameSystem extends Thread {

	private List<Entity> entities;
		
	public GameSystem(List<Entity> entities) {
		super();
		this.entities = entities;
	}
	
	public GameSystem() {
		super();
		this.entities = new LinkedList<Entity>();
	}
	
	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	@Override
	public void run() {
		super.run();
	}
}