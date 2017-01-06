package de.sloth.hmi;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.event.GameEvent;
import javafx.scene.layout.StackPane;

public class GameInterfaceLayer extends StackPane {
	private String uid;
	private ConcurrentLinkedQueue<GameEvent> eventQueue;

	public GameInterfaceLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super();
		this.uid = uid;
		this.eventQueue = eventQueue;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public ConcurrentLinkedQueue<GameEvent> getEventQueue() {
		return eventQueue;
	}

	public void setEventQueue(ConcurrentLinkedQueue<GameEvent> eventQueue) {
		this.eventQueue = eventQueue;
	}
}