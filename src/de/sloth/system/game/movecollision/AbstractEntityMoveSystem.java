package de.sloth.system.game.movecollision;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.event.MoveEvent;
import de.sloth.system.GameSystem;
import javafx.scene.canvas.GraphicsContext;

public abstract class AbstractEntityMoveSystem extends GameSystem {
	private GraphicsContext gc;
	
	public AbstractEntityMoveSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue, GraphicsContext gc) {
		super(entities, eventQueue);
		this.gc = gc;
	}
	
	@Override
	public void executeSystem() {
		List<GameEvent> delList = new LinkedList<GameEvent>();
		
		GameEvent event = this.checkEvents(MoveEvent.class);
		if(event != null) {
			MoveEvent movEvent = (MoveEvent) event;
			Entity srcEntity = movEvent.getSrcEntity();
			Position3DComp posComp = (Position3DComp) srcEntity.getComponent(Position3DComp.class);
			SpriteComp sComp = (SpriteComp) srcEntity.getComponent(SpriteComp.class);
			boolean isCollided = checkCollision(srcEntity, movEvent);
			if(movEvent.getTargetX() >= 0 && movEvent.getTargetX() < gc.getCanvas().getWidth() &&
			   movEvent.getTargetY() >= 0 && movEvent.getTargetY() < gc.getCanvas().getHeight() &&
			   !isCollided) {
				if(sComp.getDirection() == "right" && movEvent.getTargetX() < posComp.getX()) {
					sComp.setDirection("left");
					sComp.setSpritePath(srcEntity.getName() + "_left.png");
				} else if(sComp.getDirection() == "left" && movEvent.getTargetX() > posComp.getX()) {
					sComp.setDirection("right");
					sComp.setSpritePath(srcEntity.getName() + "_right.png");
				}
				posComp.setX(movEvent.getTargetX());
				posComp.setY(movEvent.getTargetY());
			}
			delList.add(event);
		}
		this.getEventQueue().removeAll(delList);
	}

	protected abstract boolean checkCollision(Entity srcEntity, MoveEvent movEvent);
}
