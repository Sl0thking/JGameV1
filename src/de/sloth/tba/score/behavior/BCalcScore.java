package de.sloth.tba.score.behavior;

import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.component.ScoreComp;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.tba.score.event.CalcScoreEvent;
import de.sloth.tba.score.other.ScoreType;

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
	public void execute(DefaultGameSystem system) {}

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent) {
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