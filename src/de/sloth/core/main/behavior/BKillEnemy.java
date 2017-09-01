package de.sloth.core.main.behavior;

import de.sloth.core.collisionHandle.behavior.BDespawn;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.sound.event.PlaySoundEvent;
import de.sloth.tba.score.event.CalcScoreEvent;
import de.sloth.tba.score.other.ScoreType;



/**
 * Behavior which extends the KillEnemy behavior. 
 * Calls CalcScore and PlaySound System for their behavior.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 18.05.2017
 *
 */
public class BKillEnemy extends BDespawn {

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent) {
		system.getEventQueue().add(new CalcScoreEvent(ScoreType.KILL));
		system.getEventQueue().add(new PlaySoundEvent("pain"));
		super.execute(system, expectedEvent);
	}
}
