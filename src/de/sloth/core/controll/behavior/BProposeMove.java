package de.sloth.core.controll.behavior;

import de.sloth.core.controll.event.PossibleMoveEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.movement.event.Direction;

public class BProposeMove implements IBehavior {
	
	private Direction associatedDirection;
	
	public BProposeMove(Direction associatedDirection) {
		super();
		this.associatedDirection = associatedDirection;
	}

	@Override
	public void execute(DefaultGameSystem system) {
		GameEvent event = new PossibleMoveEvent(this.associatedDirection);
		system.getEventQueue().add(event);
	}

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent){}

}