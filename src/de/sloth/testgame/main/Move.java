package de.sloth.testgame.main;

import de.sloth.component.Position3DComp;
import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class Move implements IBehavior{

	private int speed;
	private int maxY;
	private int maxX;
	
	public Move(int speed, int maxX, int maxY) {
		this.speed = speed;
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
			
		Position3DComp posComp = (Position3DComp) movEntity.getComponent(Position3DComp.class);
		if(mEvent.getDirection().equals(Direction.TOP)) {
			if(maxY == -1 || posComp.getY() <= maxY) {
				posComp.setY(posComp.getY()+speed);
			}
		}
		
		if(mEvent.getDirection().equals(Direction.BOTTOM)) {
			if(posComp.getY()-speed > 0) {
				posComp.setY(posComp.getY()-speed);
			}
		}
			
		if(mEvent.getDirection().equals(Direction.RIGHT)) {
			if(maxX == -1 || posComp.getX()+speed <= maxX) {
				posComp.setX(posComp.getX()+speed);
			}
		}
			
		if(mEvent.getDirection().equals(Direction.LEFT)) {
			if(posComp.getX()-speed > 0) {
				posComp.setX(posComp.getX()-speed);
			}
		}
	}
}
