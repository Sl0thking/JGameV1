package de.sloth.core.main.system;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;

public class GameSystem {

	private List<GameEvent> eventQueue;
	private Map<String, IBehavior> keywordMapping;
	private Class<? extends GameEvent> listeningEvent;
	private boolean isActive;
	private boolean isQuiet;
	private String systemID;
	private IEntityManagement entityManager;
	
	public GameSystem(String systemID, Class<? extends GameEvent> listeningEvent, IEntityManagement entityManager, List<GameEvent> eventQueue) {
		super();
		this.setEntityManager(entityManager);
		this.eventQueue = eventQueue;
		this.setListeningEvent(listeningEvent);
		this.isActive = true;
		this.keywordMapping = new HashMap<String, IBehavior>();
		this.setSystemID(systemID);
		this.setQuiet(true);
	}
	
	public GameSystem(String systemID, IEntityManagement entityManager) {
		super();
		this.setEntityManager(entityManager);
		this.eventQueue = new LinkedList<GameEvent>();
		this.isActive = true;
		this.setListeningEvent(null);
		this.keywordMapping = new HashMap<String, IBehavior>();
		this.setSystemID(systemID);
		this.setQuiet(true);
	}
	
	public List<GameEvent> getEventQueue() {
		return eventQueue;
	}

	public void setEventQueue(List<GameEvent> eventQueue) {
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
	
	public void executeSystem() throws Exception {
		if(this.isActive) {
			if(listeningEvent != null) {
				List<GameEvent> delList = new LinkedList<GameEvent>();
				for(GameEvent event : this.eventQueue) {
					if(event.getClass().equals(this.listeningEvent)) {
						IBehavior behavior = this.getBehavior(event);
						if(behavior != null) {
							behavior.execute(this, event);
						}
						delList.add(event);
					}
				}
				this.getEventQueue().removeAll(delList);
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

	public boolean isQuiet() {
		return isQuiet;
	}

	public void setQuiet(boolean isQuiet) {
		this.isQuiet = isQuiet;
	}

	@Override
	public String toString() {
		return "GameSystem [eventQueue=" + eventQueue + ", keywordMapping="
				+ keywordMapping + ", listeningEvent=" + listeningEvent
				+ ", isActive=" + isActive + ", isQuiet=" + isQuiet
				+ ", systemID=" + systemID + ", entityManager=" + entityManager
				+ "]";
	}
	
	
}