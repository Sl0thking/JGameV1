package de.sloth.controllHandler;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.event.HMIEvent;
import de.sloth.event.HMIKeyword;
import de.sloth.event.InventoryEvent;
import de.sloth.event.InventoryKeyword;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InventoryControllHandler implements EventHandler<KeyEvent> {

	private ConcurrentLinkedQueue<Entity> entities;
	private ConcurrentLinkedQueue<GameEvent> eventQueue;
	
	public InventoryControllHandler(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		this.entities = entities;
		this.eventQueue = eventQueue;
	}
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode().equals(KeyCode.W)) {
			InventoryEvent iEvent = new InventoryEvent(InventoryKeyword.changeCursor);
			iEvent.setxPos(-5);	
			this.eventQueue.add(iEvent);
		} else if(event.getCode().equals(KeyCode.S)) {
			InventoryEvent iEvent = new InventoryEvent(InventoryKeyword.changeCursor);
			iEvent.setxPos(5);	
			this.eventQueue.add(iEvent);
		} else if(event.getCode().equals(KeyCode.A)) {
			InventoryEvent iEvent = new InventoryEvent(InventoryKeyword.changeCursor);
			iEvent.setxPos(-1);	
			this.eventQueue.add(iEvent);
		} else if(event.getCode().equals(KeyCode.D)) {
			InventoryEvent iEvent = new InventoryEvent(InventoryKeyword.changeCursor);
			iEvent.setxPos(1);	
			this.eventQueue.add(iEvent);
		} else if(event.getCode().equals(KeyCode.I) || event.getCode().equals(KeyCode.ESCAPE)) {
			GameEvent toggleInventory = new HMIEvent(HMIKeyword.closeInventory);
			this.eventQueue.add(toggleInventory);
		} else if(event.getCode().equals(KeyCode.SPACE)) {
			InventoryEvent iEvent = new InventoryEvent(InventoryKeyword.useItem);
			this.eventQueue.add(iEvent);
		}
	}
}