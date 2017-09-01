package de.sloth.core.controll.factory;

import java.util.HashMap;

import de.sloth.core.controll.behavior.BProposeMove;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.factory.IBehaviorFactory;
import de.sloth.core.movement.event.Direction;

public class BProposeMoveFactory implements IBehaviorFactory {

	@Override
	public IBehavior createBehavior(HashMap<String, String> attrNameToValueMap)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		Direction direction = Direction.valueOf(attrNameToValueMap.get("direction").toUpperCase());
		BProposeMove bProposeMove = new BProposeMove(direction);
		return bProposeMove;
	}

}
