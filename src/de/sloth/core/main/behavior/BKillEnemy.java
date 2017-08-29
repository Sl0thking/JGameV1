package de.sloth.core.main.behavior;

import de.sloth.core.collision.behavior.Despawn;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;
import de.sloth.core.score.event.CalcScoreEvent;
import de.sloth.core.score.other.ScoreType;
import de.sloth.core.sound.event.PlaySoundEvent;



/**
 * Behavior which extends the KillEnemy behavior. 
 * Calls CalcScore and PlaySound System for their behavior.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 18.05.2017
 *
 */
public class BKillEnemy extends Despawn {

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		system.getEventQueue().add(new CalcScoreEvent(ScoreType.KILL));
		system.getEventQueue().add(new PlaySoundEvent("pain"));
		super.execute(system, expectedEvent);
	}
}
