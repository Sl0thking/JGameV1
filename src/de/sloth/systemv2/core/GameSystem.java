package de.sloth.systemv2.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;

public class GameSystem {

	private ConcurrentLinkedQueue<Entity> entities;
	private ConcurrentLinkedQueue<GameEvent> eventQueue;
	private Map<String, IBehavior> keywordMapping;
	private Class<? extends GameEvent> listeningEvent;
	private boolean isActive;
	private String systemID;
	
	public GameSystem(String systemID, Class<? extends GameEvent> listeningEvent, ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super();
		this.entities = entities;
		this.eventQueue = eventQueue;
		this.setListeningEvent(listeningEvent);
		this.isActive = true;
		this.keywordMapping = new HashMap<String, IBehavior>();
		this.setSystemID(systemID);
	}
	
	public GameSystem(String systemID) {
		super();
		this.entities = new ConcurrentLinkedQueue<Entity>();
		this.eventQueue = new ConcurrentLinkedQueue<GameEvent>();
		this.isActive = true;
		this.setListeningEvent(null);
		this.keywordMapping = new HashMap<String, IBehavior>();
		this.setSystemID(systemID);
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
	
	public void registerBehavior(String keyword, IBehavior behavior) {
		this.keywordMapping.put(keyword, behavior);
	}

	public List<Entity> filterEntitiesByComponent(Class<?> compClass) {
		List<Entity> matchingEntities = new LinkedList<Entity>();
		for(Entity entity : this.entities) {
			if(entity.getComponentClasses().contains(compClass)) {
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
		if(listeningEvent != null) {
			System.out.println(this.getEventQueue());
			for(GameEvent event:this.getEventQueue()) {
				if(event.getClass().equals(this.listeningEvent)) {
					//System.out.println(this + " / GOTCHA / " + event);
					IBehavior behavior = this.keywordMapping.get(event.getKeyword());
					if(behavior != null) {
						behavior.execute(this, event);
					}
				}
			}
		} else {
			this.keywordMapping.get("Any").execute(this);
		}
	}

	public Class<? extends GameEvent> getListeningEvent() {
		return listeningEvent;
	}

	public void setListeningEvent(Class<? extends GameEvent> listeningEvent2) {
		this.listeningEvent = listeningEvent2;
	}

	public String getSystemID() {
		return systemID;
	}

	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}

	@Override
	public String toString() {
		return "GameSystem [entities=" + entities + ", keywordMapping=" + keywordMapping
				+ ", listeningEvent=" + listeningEvent + ", isActive="
				+ isActive + ", systemID=" + systemID + "]";
	}
}