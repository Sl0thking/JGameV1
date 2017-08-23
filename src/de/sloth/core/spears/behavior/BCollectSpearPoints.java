package de.sloth.core.spears.behavior;

import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.score.event.CalcScoreEvent;
import de.sloth.core.score.other.ScoreType;

/**
 * Behavior to get points when collecting spears.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 22.05.2017
 */
public class BCollectSpearPoints extends BCollectSpear {

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		system.getEventQueue().add(new CalcScoreEvent(ScoreType.COLLECT));
		super.execute(system, expectedEvent);
	}
}