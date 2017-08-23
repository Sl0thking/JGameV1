package de.sloth.core.score.behavior;

import de.sloth.core.main.Entity;
import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;
import de.sloth.core.main.component.ScoreComp;
import de.sloth.core.score.event.CalcScoreEvent;
import de.sloth.core.score.other.ScoreType;

/**
 * Behavior of score calculation for the score system.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 22.05.2017
 */
public class BCalcScore implements IBehavior {

	private int calls = 0;
	
	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		CalcScoreEvent cse = (CalcScoreEvent) expectedEvent;
		Entity player = system.getEntityManager().getActivePlayabaleEntity();
		if(player != null) {
			ScoreComp scComp = (ScoreComp) player.getComponent(ScoreComp.class);
			if(cse.getsType().equals(ScoreType.SURVIVAL)) {
				this.calls += 1;
				if(calls > 199) {
					scComp.setScore(scComp.getScore() + 1);
					calls = 0;
				}
			} else if (cse.getsType().equals(ScoreType.KILL)) {
				scComp.setScore(scComp.getScore() + 100);
				//collect
			} else {
				scComp.setScore(scComp.getScore() + 50);
			}
		}
	}
}