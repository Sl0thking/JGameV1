package de.sloth.system;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;

public abstract class GameSystem {

	private ConcurrentLinkedQueue<Entity> entities;
	private ConcurrentLinkedQueue<GameEvent> eventQueue;
	private boolean isActive;
	
	public GameSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super();
		this.entities = entities;
		this.eventQueue = eventQueue;
		this.isActive = true;
	}
	
	public GameSystem() {
		super();
		this.entities = new ConcurrentLinkedQueue<Entity>();
		this.eventQueue = new ConcurrentLinkedQueue<GameEvent>();
		this.isActive = true;
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
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
			if(entity.getComponentClasses().containsAll(compClasses)) {
				matchingEntities.add(entity);
			}
		}
		return matchingEntities;
	}
	
	public GameEvent checkEvents(Class<?> triggerEventClass) {
		for(GameEvent event:this.getEventQueue()) {
			if(event.getClass().equals(triggerEventClass)) {
				return event;
			} 
		}
		return null;
	}
	
	public void executeSystem() {
		for(GameEvent event:this.getEventQueue()) {
			if(event.getClass().equals(SystemActivationEvent.class)) {
				SystemActivationEvent sae = (SystemActivationEvent) event;
				if(sae.getTargetSystem().equals(this.getClass())) {
					this.isActive = sae.isActive();
					this.getEventQueue().remove(event);
				}
			}
		}
	}
}