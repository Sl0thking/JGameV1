package de.sloth.system.game.collision;

import java.util.List;

import de.sloth.component.HitboxComp;
import de.sloth.component.MovableComp;
import de.sloth.component.Position3DComp;
import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.game.moveSystem.Direction;
import de.sloth.system.game.moveSystem.MoveEvent;
import de.sloth.system.game.moveSystem.PossibleMoveEvent;

public class CheckCollision implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		PossibleMoveEvent pMvEvent = (PossibleMoveEvent) expectedEvent;
		Entity movingEntity = pMvEvent.getEntity();
		boolean isCollided = false;
		if(movingEntity == null) {
			movingEntity = system.getEntityManager().getActivePlayabaleEntity();
		}
		Direction direction = pMvEvent.getDirection();
		List<Entity> entities = system.getEntityManager().getAllEntities();
		for(Entity entity : entities) {
			if(intersectionCheck(movingEntity, direction, entity) && !movingEntity.equals(entity)) {
				system.getEventQueue().add(new CollisionEvent(movingEntity, entity));
				isCollided = true;
			}
		}
		if(!isCollided) {
			system.getEventQueue().add(new MoveEvent(pMvEvent.getEntity(), pMvEvent.getDirection()));
		}
	}
	
	private boolean intersectionCheck(Entity movingEntity, Direction direction, Entity possibleIntersectionEntity) {
		if(movingEntity != null && direction != null & possibleIntersectionEntity != null) {
			Position3DComp mov3DComp = (Position3DComp) movingEntity.getComponent(Position3DComp.class);
			HitboxComp movHitbox = (HitboxComp) movingEntity.getComponent(HitboxComp.class);
			MovableComp movMov = (MovableComp) movingEntity.getComponent(MovableComp.class);
			Position3DComp coll3DComp = (Position3DComp) possibleIntersectionEntity.getComponent(Position3DComp.class);
			HitboxComp collHitbox = (HitboxComp) possibleIntersectionEntity.getComponent(HitboxComp.class);
			int movX = mov3DComp.getX();
			int movY = mov3DComp.getY();
		
			if(coll3DComp == null || collHitbox == null) {
				return false;
			}
		
			if(direction.equals(Direction.TOP)) {
				movY += movMov.getSpeed();
			} else if(direction.equals(Direction.BOTTOM)) {
				movY -= movMov.getSpeed();
			} else if(direction.equals(Direction.LEFT)) {
				movX -= movMov.getSpeed();
			} else if(direction.equals(Direction.RIGHT)) {
				movX += movMov.getSpeed();
			}
		
			return !(movX + movHitbox.getWidth() < coll3DComp.getX() ||
					coll3DComp.getX() + collHitbox.getWidth() < movX ||
					movY + movHitbox.getHeight() < coll3DComp.getY() ||
					coll3DComp.getY() + collHitbox.getHeight() < movY);
		} else {
			return false;
		}		
	}
}