package de.sloth.system.game.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.system.game.systemActivation.SystemActivationEvent;

public class GameSystem {

	private ConcurrentLinkedQueue<GameEvent> eventQueue;
	private Map<String, IBehavior> keywordMapping;
	private Class<? extends GameEvent> listeningEvent;
	private boolean isActive;
	private String systemID;
	private IEntityManagement entityManager;
	private ConfigLoader cl;
	
	public GameSystem(String systemID, Class<? extends GameEvent> listeningEvent, IEntityManagement entityManager, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super();
		this.setEntityManager(entityManager);
		this.eventQueue = eventQueue;
		this.setListeningEvent(listeningEvent);
		this.isActive = true;
		this.keywordMapping = new HashMap<String, IBehavior>();
		this.setSystemID(systemID);
	}
	
	public GameSystem(String systemID, IEntityManagement entityManager) {
		super();
		this.setEntityManager(entityManager);
		this.eventQueue = new ConcurrentLinkedQueue<GameEvent>();
		this.isActive = true;
		this.setListeningEvent(null);
		this.keywordMapping = new HashMap<String, IBehavior>();
		this.setSystemID(systemID);
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
/*
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
*/	
	public GameEvent checkEvents(Class<?> triggerEventClass) {
		for(GameEvent event:this.getEventQueue()) {
			if(event.getClass().equals(triggerEventClass)) {
				return event;
			} 
		}
		return null;
	}
	
	public IBehavior getBehavior(GameEvent event) {
		return this.keywordMapping.get(event.getKeyword());
	}
	
	public void executeSystem() {
		if(this.isActive) {
			if(listeningEvent != null) {
				ConcurrentLinkedQueue<GameEvent> currEvents = this.getEventQueue();
				for(GameEvent event:currEvents) {
					if(event.getClass().equals(this.listeningEvent)) {
						IBehavior behavior = this.getBehavior(event);
						if(behavior != null) {
							behavior.execute(this, event);
						}
						this.getEventQueue().remove(event);
					}
				}
			} else {
				this.keywordMapping.get("Any").execute(this);
			}
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

	public IEntityManagement getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(IEntityManagement entityManager) {
		this.entityManager = entityManager;
	}

	public Map<String, IBehavior> getKeywordMapping() {
		return keywordMapping;
	}

	public void setKeywordMapping(Map<String, IBehavior> keywordMapping) {
		this.keywordMapping = keywordMapping;
	}
}