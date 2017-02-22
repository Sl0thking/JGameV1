package de.sloth.system.game.collision.commonBehavior;

import de.sloth.component.Position3DComp;
import de.sloth.system.game.collision.CollisionEvent;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class Deglitch implements IBehavior {

	private final int CANVAS_WIDTH = 640;
	
	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		CollisionEvent cevent = (CollisionEvent) expectedEvent;
		Position3DComp tPosComp = (Position3DComp) cevent.getCollisionTarget().getComponent(Position3DComp.class);
		Position3DComp srcPosComp = (Position3DComp) cevent.getCollisionSrc().getComponent(Position3DComp.class);
		if(srcPosComp.getX() < tPosComp.getX()) {
			if(srcPosComp.getX()-16 > 0) {
				srcPosComp.setX(srcPosComp.getX()-16);
			} else {
				srcPosComp.setX(0);
			}
			if(tPosComp.getX()+16 < CANVAS_WIDTH) {
				tPosComp.setX(tPosComp.getX()+16);
			} else {
				tPosComp.setX(CANVAS_WIDTH);
			}
		} else {
			if(tPosComp.getX()-16 > 0) {
				tPosComp.setX(tPosComp.getX()-16);
			} else {
				tPosComp.setX(0);
			}
			if(srcPosComp.getX()+16 < CANVAS_WIDTH) {
				srcPosComp.setX(srcPosComp.getX()+16);
			} else {
				srcPosComp.setX(CANVAS_WIDTH);
			}
		}
	}
}
