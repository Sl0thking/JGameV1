package de.sloth.ai.neuralNetwork.behavior;

import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.component.HealthComp;
import de.sloth.core.main.component.ScoreComp;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;
import de.sloth.ai.neuralNetwork.component.NeuralNetworkComp;
import de.sloth.ai.neuralNetwork.event.GeneticalEvent;
import de.sloth.ai.neuralNetwork.main.EntityManagerNN;
import de.sloth.core.score.event.CalcScoreEvent;
import de.sloth.core.score.other.ScoreType;

/**
 * System to check player death in neural network mode.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 20.05.2017
 *
 */
public class BCheckForDeathNN implements IBehavior {

	@Override
	public void execute(GameSystem system) {
		Entity player = system.getEntityManager().getActivePlayabaleEntity();
		Entity nnEntity = ((EntityManagerNN) system.getEntityManager()).getNNInformation();
		HealthComp hComp = (HealthComp) player.getComponent(HealthComp.class);
		NeuralNetworkComp nnComp = (NeuralNetworkComp) nnEntity.getComponent(NeuralNetworkComp.class);
		ScoreComp scComp = (ScoreComp) player.getComponent(ScoreComp.class);
		if(hComp.getLifes() <= 0) {
			nnComp.getNetwork().getSequence().setFitnessLvl(scComp.getScore());
			if(!system.isQuiet()) {
				System.out.println("[EndConditionSys::CheckForDeathNN] Current candidate scored: " + nnComp.getNetwork().getSequence().getFitnessLvl());
			}
			system.getEventQueue().add(new GeneticalEvent());
		} else {
			system.getEventQueue().add(new CalcScoreEvent(ScoreType.SURVIVAL));
		}
	}

	@Override
	public void execute(GameSystem arg0, GameEvent arg1) {}
}