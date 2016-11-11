package de.sloth.system;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.Position2DComp;
import de.sloth.entity.Entity;
import de.sloth.event.CollisionEvent;
import de.sloth.event.GameEvent;
import de.sloth.event.MoveEvent;
import javafx.scene.canvas.GraphicsContext;

public abstract class AbstractEntityMoveSystem extends GameSystem {
	private GraphicsContext gc;
	
	public AbstractEntityMoveSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue, GraphicsContext gc) {
		super(entities, eventQueue);
		this.gc = gc;
	}
	
	@Override
	public synchronized void run() {
		while(true) {
			List<GameEvent> delList = new LinkedList<GameEvent>();
			for(GameEvent event: this.getEventQueue()) {
				if(event.getClass().equals(MoveEvent.class)) {
					MoveEvent movEvent = (MoveEvent) event;
					Entity srcEntity = movEvent.getSrcEntity();
					Position2DComp twodcomp = (Position2DComp) srcEntity.getComponent(Position2DComp.class);
					boolean isCollided = checkCollision(srcEntity, movEvent);
					if(movEvent.getTargetX() >= 0 && movEvent.getTargetX() < gc.getCanvas().getWidth() &&
					   movEvent.getTargetY() >= 0 && movEvent.getTargetY() < gc.getCanvas().getHeight() &&
					   !isCollided) {
						twodcomp.setX(movEvent.getTargetX());
						twodcomp.setY(movEvent.getTargetY());
					}
					delList.add(event);
				}
			}
			this.getEventQueue().removeAll(delList);
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	protected abstract boolean checkCollision(Entity srcEntity, MoveEvent movEvent);
	
	/*protected abstract boolean checkCollision(Entity srcEntity, MoveEvent movEvent) {
		for(Entity entity : this.getEntities()) {
			if(entity.getId() != srcEntity.getId()) {
				Position2DComp twodcomp = (Position2DComp) entity.getComponent(Position2DComp.class);
				if(twodcomp != null && twodcomp.getX() == movEvent.getTargetX() && twodcomp.getY() == movEvent.getTargetY()) {
					GameEvent collision = new CollisionEvent(srcEntity, entity);
					this.getEventQueue().add(collision);
					return true;
				}
			}
		}
		return false;
	}*/
}
