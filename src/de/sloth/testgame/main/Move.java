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
		System.out.println("FOUND EVENT");
		Entity player = system.getEntityManager().getActivePlayabaleEntity();
		System.out.println(player);
		Position3DComp posComp = (Position3DComp) player.getComponent(Position3DComp.class);
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
				System.out.println("MOVE RIGHT");
				posComp.setX(posComp.getX()+speed);
			}
		}
		
		if(mEvent.getDirection().equals(Direction.LEFT)) {
			System.out.println("DIRECTION WAS LEFT");
			if(posComp.getX()-speed > 0) {
				System.out.println("MOVE LEFT");
				posComp.setX(posComp.getX()-speed);
			}
		}
	}
}
