package de.sloth.controllHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.FocusComp;
import de.sloth.component.Position3DComp;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.event.HMIEvent;
import de.sloth.event.HMIKeyword;
import de.sloth.event.MoveEvent;
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
		if(event.getCode().equals(KeyCode.W) ||
		   event.getCode().equals(KeyCode.S) ||
		   event.getCode().equals(KeyCode.A) ||
		   event.getCode().equals(KeyCode.D)) {
				System.out.println("INVENTORY");
		} else if(event.getCode().equals(KeyCode.I)) {
			GameEvent toggleInventory = new HMIEvent(HMIKeyword.closeInventory);
			this.eventQueue.add(toggleInventory);
		} else if(event.getCode().equals(KeyCode.ESCAPE)) {
			System.exit(0);
		}
	}
}