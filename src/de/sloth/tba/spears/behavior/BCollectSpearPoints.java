package de.sloth.tba.spears.behavior;

import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.tba.score.event.CalcScoreEvent;
import de.sloth.tba.score.other.ScoreType;

/**
 * Behavior to get points when collecting spears.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 22.05.2017
 */
public class BCollectSpearPoints extends BCollectSpear {

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent) {
		system.getEventQueue().add(new CalcScoreEvent(ScoreType.COLLECT));
		super.execute(system, expectedEvent);
	}
}