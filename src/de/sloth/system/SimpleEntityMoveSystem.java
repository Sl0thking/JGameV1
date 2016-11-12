package de.sloth.system;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.Position3DComp;
import de.sloth.entity.Entity;
import de.sloth.event.CollisionEvent;
import de.sloth.event.GameEvent;
import de.sloth.event.MoveEvent;
import javafx.scene.canvas.GraphicsContext;

public class SimpleEntityMoveSystem extends AbstractEntityMoveSystem {
	
	public SimpleEntityMoveSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue, GraphicsContext gc) {
		super(entities, eventQueue, gc);
	}
	
	@Override
	protected boolean checkCollision(Entity srcEntity, MoveEvent movEvent) {
		for(Entity entity : this.getEntities()) {
			if(entity.getId() != srcEntity.getId()) {
				Position3DComp posComp = (Position3DComp) entity.getComponent(Position3DComp.class);
				if(posComp != null && posComp.getX() == movEvent.getTargetX() && 
				   posComp.getY() == movEvent.getTargetY() &&
				   posComp.getZ() == movEvent.getTargetZ()) {
					GameEvent collision = new CollisionEvent(srcEntity, entity);
					this.getEventQueue().add(collision);
					return true;
				}
			}
		}
		return false;
	}
}
