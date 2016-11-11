package de.sloth.system;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;

public abstract class GameSystem extends Thread {

	private ConcurrentLinkedQueue<Entity> entities;
	private ConcurrentLinkedQueue<GameEvent> eventQueue;
	
	public GameSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super();
		this.setDaemon(true);
		this.entities = entities;
		this.eventQueue = eventQueue;
	}
	
	public GameSystem() {
		super();
		this.setDaemon(true);
		this.entities = new ConcurrentLinkedQueue<Entity>();
		this.eventQueue = new ConcurrentLinkedQueue<GameEvent>();
	}
	
	public ConcurrentLinkedQueue<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ConcurrentLinkedQueue<Entity> entities) {
		this.entities = entities;
	}

	public ConcurrentLinkedQueue<GameEvent> getEventQueue() {
		return eventQueue;
	}

	public void setEventQueue(ConcurrentLinkedQueue<GameEvent> eventQueue) {
		this.eventQueue = eventQueue;
	}
	
	public List<Entity> filterEntitiesByComponent(Class<?> compClass) {
		List<Entity> matchingEntities = new LinkedList<Entity>();
		for(Entity entity : this.entities) {
			if(entity.getComponents().contains(compClass)) {
				matchingEntities.add(entity);
			}
		}
		return matchingEntities;
	}

	public List<Entity> filterEntitiesByComponents(List<Class<?>> compClasses) {
		List<Entity> matchingEntities = new LinkedList<Entity>();
		for(Entity entity : this.entities) {
			if(entity.getComponents().containsAll(compClasses)) {
				matchingEntities.add(entity);
			}
		}
		return matchingEntities;
	}
	
	@Override
	public void run() {
		super.run();
	}
}