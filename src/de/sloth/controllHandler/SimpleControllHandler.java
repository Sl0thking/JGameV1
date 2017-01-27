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

public class SimpleControllHandler implements EventHandler<KeyEvent> {

	private int spriteWidth;
	private int spriteHeight;
	private ConcurrentLinkedQueue<Entity> entities;
	private ConcurrentLinkedQueue<GameEvent> eventQueue;
	
	public SimpleControllHandler(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue, int spriteWidth, int spriteHeight) {
		this.entities = entities;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.eventQueue = eventQueue;
	}
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode().equals(KeyCode.W) ||
		   event.getCode().equals(KeyCode.S) ||
		   event.getCode().equals(KeyCode.A) ||
		   event.getCode().equals(KeyCode.D)) {
				handleFocusMovement(event.getCode());
		} else if(event.getCode().equals(KeyCode.G)) {
			Entity entity = new Entity();
			entity.setId(getId()+1);
			entity.addComponent(new Position3DComp());
			((Position3DComp) entity.getComponent(Position3DComp.class)).setX(128);
			((Position3DComp) entity.getComponent(Position3DComp.class)).setY(256);
			entities.add(entity);
		} else if(event.getCode().equals(KeyCode.Q)) {
			GameEvent toggleEvent = new HMIEvent(HMIKeyword.togglePlayerInfo);
			this.eventQueue.add(toggleEvent);
		} else if(event.getCode().equals(KeyCode.I)) {
			GameEvent toggleInventory = new HMIEvent(HMIKeyword.showInventory);
			this.eventQueue.add(toggleInventory);
		} else if(event.getCode().equals(KeyCode.ESCAPE)) {
			this.eventQueue.add(new HMIEvent(HMIKeyword.showMenu));
			this.entities.clear();
		}
	}
	
	private int getId() {
		int maxId = 0;
		for(Entity entity : this.entities) {
			if(entity.getId() > maxId) {
				maxId = entity.getId();
			}
		}
		return maxId;
	}
	
	private void handleFocusMovement(KeyCode kCode) {
		MoveEvent moveEvent = new MoveEvent();

		List<Entity> filteredEntities = new ArrayList<Entity>();
		
		for(Entity entity : this.entities) {
			if(entity.getComponent(FocusComp.class) != null) {
				filteredEntities.add(entity);
			}
		}
		for(Entity focusEntity : filteredEntities) {
			if(kCode.equals(KeyCode.W)) {
				Position3DComp comp = (Position3DComp) focusEntity.getComponent(Position3DComp.class);
				moveEvent.setTargetY(comp.getY()-1);
				moveEvent.setTargetX(comp.getX());
				moveEvent.setTargetZ(1);
			} else if(kCode.equals(KeyCode.S)) {
				Position3DComp comp = (Position3DComp) focusEntity.getComponent(Position3DComp.class);
				moveEvent.setTargetY(comp.getY()+1);
				moveEvent.setTargetX(comp.getX());
				moveEvent.setTargetZ(1);
			} else if(kCode.equals(KeyCode.A)) {
				Position3DComp comp = (Position3DComp) focusEntity.getComponent(Position3DComp.class);
				moveEvent.setTargetX(comp.getX()-1);
				moveEvent.setTargetY(comp.getY());
				moveEvent.setTargetZ(1);
			} else if(kCode.equals(KeyCode.D)) {
				Position3DComp comp = (Position3DComp) focusEntity.getComponent(Position3DComp.class);
				moveEvent.setTargetX(comp.getX()+1);
				moveEvent.setTargetY(comp.getY());
				moveEvent.setTargetZ(1);
			}
			moveEvent.setSrcEntity(focusEntity);
			this.eventQueue.add(moveEvent);
		}
	}
}
