package de.sloth.controllHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.FocusComp;
import de.sloth.component.Position2DComp;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.event.MoveEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SimpleControllHandler implements EventHandler<KeyEvent> {

	private int speed;
	private ConcurrentLinkedQueue<Entity> entities;
	private ConcurrentLinkedQueue<GameEvent> eventQueue;
	
	public SimpleControllHandler(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue, int speed) {
		this.entities = entities;
		this.speed = speed;
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
			entity.addComponent(new Position2DComp());
			((Position2DComp) entity.getComponent(Position2DComp.class)).setX(128);
			((Position2DComp) entity.getComponent(Position2DComp.class)).setY(256);
			entities.add(entity);
		} else if(event.getCode().equals(KeyCode.ESCAPE)) {
			System.exit(0);
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
				Position2DComp comp = (Position2DComp) focusEntity.getComponent(Position2DComp.class);
				moveEvent.setTargetY(comp.getY()-speed);
				moveEvent.setTargetX(comp.getX());
			} else if(kCode.equals(KeyCode.S)) {
				Position2DComp comp = (Position2DComp) focusEntity.getComponent(Position2DComp.class);
				moveEvent.setTargetY(comp.getY()+speed);
				moveEvent.setTargetX(comp.getX());
			} else if(kCode.equals(KeyCode.A)) {
				Position2DComp comp = (Position2DComp) focusEntity.getComponent(Position2DComp.class);
				moveEvent.setTargetX(comp.getX()-speed);
				moveEvent.setTargetY(comp.getY());
			} else if(kCode.equals(KeyCode.D)) {
				Position2DComp comp = (Position2DComp) focusEntity.getComponent(Position2DComp.class);
				moveEvent.setTargetX(comp.getX()+speed);
				moveEvent.setTargetY(comp.getY());
			}
			moveEvent.setSrcEntity(focusEntity);
			this.eventQueue.add(moveEvent);
		}
		/*
		List<Entity> filteredEntities = new ArrayList<Entity>();
	
		for(Entity entity : this.entities) {
			if(entity.getComponents(FocusComp.class).size() != 0) {
				filteredEntities.add(entity);
			}
		}
		for(Entity focusEntity : filteredEntities) {
			if(kCode.equals(KeyCode.W)) {
				Position2DComp comp = (Position2DComp) focusEntity.getComponents(Position2DComp.class).get(0);
				comp.setY(comp.getY()-speed);
			} else if(kCode.equals(KeyCode.S)) {
				Position2DComp comp = (Position2DComp) focusEntity.getComponents(Position2DComp.class).get(0);
				comp.setY(comp.getY()+speed);
			} else if(kCode.equals(KeyCode.A)) {
				Position2DComp comp = (Position2DComp) focusEntity.getComponents(Position2DComp.class).get(0);
				comp.setX(comp.getX()-speed);
			} else if(kCode.equals(KeyCode.D)) {
				Position2DComp comp = (Position2DComp) focusEntity.getComponents(Position2DComp.class).get(0);
				comp.setX(comp.getX()+speed);
			}
		}
		*/
	}
}
