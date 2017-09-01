package de.sloth.core.movement.behavior;

import de.sloth.core.collisionCheck.event.MoveEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.component.SpriteComp;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.movement.component.MovableComp;
import de.sloth.core.movement.component.Position3DComp;
import de.sloth.core.movement.event.Direction;

public class Move implements IBehavior{
	private int maxY;
	private int maxX;
	
	public Move(int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	public Move() {
		this.maxX = 480;
		this.maxY = 640;
	}
	
	@Override
	public void execute(DefaultGameSystem system) {}

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent) {
		MoveEvent mEvent = (MoveEvent) expectedEvent;
		Entity movEntity;
		if(mEvent.getEntity() == null) {
			movEntity = system.getEntityManager().getActivePlayabaleEntity();
		} else {
			movEntity = mEvent.getEntity();
		}
		if(movEntity != null) {
			MovableComp mComp = (MovableComp) movEntity.getComponent(MovableComp.class);	
			Position3DComp posComp = (Position3DComp) movEntity.getComponent(Position3DComp.class);
			SpriteComp spComp = (SpriteComp) movEntity.getComponent(SpriteComp.class);
			if(mEvent.getDirection().equals(Direction.UP)) {
				if(maxY == -1 || posComp.getY() <= maxY) {
					posComp.setY(posComp.getY()+mComp.getSpeed());
				}
			}
		
			if(mEvent.getDirection().equals(Direction.DOWN)) {
				if(posComp.getY()-mComp.getSpeed() > 0) {
					posComp.setY(posComp.getY()-mComp.getSpeed());
				}
			}
				
			if(mEvent.getDirection().equals(Direction.RIGHT)) {
				if(maxX == -1 || posComp.getX()+mComp.getSpeed() < maxX) {
					posComp.setX(posComp.getX()+mComp.getSpeed());
				} else {
					posComp.setX(maxX);
				}
			}
				
			if(mEvent.getDirection().equals(Direction.LEFT)) {
				if(posComp.getX()-mComp.getSpeed() > 0) {
					posComp.setX(posComp.getX()-mComp.getSpeed());
				} else {
					posComp.setX(0);
				}
			}
		}
	}
}
