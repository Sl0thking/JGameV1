package de.sloth.system.game.moveSystem;

import de.sloth.component.MovableComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class Move implements IBehavior{
	private int maxY;
	private int maxX;
	
	public Move(int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		MoveEvent mEvent = (MoveEvent) expectedEvent;
		Entity movEntity;
		if(mEvent.getEntity() == null) {
			movEntity = system.getEntityManager().getActivePlayabaleEntity();
		} else {
			movEntity = mEvent.getEntity();
		}
		MovableComp mComp = (MovableComp) movEntity.getComponent(MovableComp.class);	
		Position3DComp posComp = (Position3DComp) movEntity.getComponent(Position3DComp.class);
		SpriteComp spComp = (SpriteComp) movEntity.getComponent(SpriteComp.class);
		if(mEvent.getDirection().equals(Direction.TOP)) {
			if(maxY == -1 || posComp.getY() <= maxY) {
				posComp.setY(posComp.getY()+mComp.getSpeed());
			}
		}
		
		if(mEvent.getDirection().equals(Direction.BOTTOM)) {
			if(posComp.getY()-mComp.getSpeed() > 0) {
				posComp.setY(posComp.getY()-mComp.getSpeed());
			}
		}
			
		if(mEvent.getDirection().equals(Direction.RIGHT)) {
			if(maxX == -1 || posComp.getX()+mComp.getSpeed() < maxX) {
				posComp.setX(posComp.getX()+mComp.getSpeed());
			}
		}
			
		if(mEvent.getDirection().equals(Direction.LEFT)) {
			if(posComp.getX()-mComp.getSpeed() > 0) {
				posComp.setX(posComp.getX()-mComp.getSpeed());
			}
		}
	}
}
