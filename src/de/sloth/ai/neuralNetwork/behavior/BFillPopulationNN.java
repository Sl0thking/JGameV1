package de.sloth.ai.neuralNetwork.behavior;

import java.util.List;
import java.util.Random;

import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.event.StartGameEvent;
import de.sloth.core.main.system.GameSystem;
import de.sloth.ai.neuralNetwork.component.NeuralNetworkComp;
import de.sloth.ai.neuralNetwork.component.datatype.NetworkSequence;
import de.sloth.ai.neuralNetwork.main.EntityManagerNN;

/**
 * Behavior for initializing a start population
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 20.05.2017
 */
public class BFillPopulationNN implements IBehavior{

	private static final int BIT_RANGE = 8;
	private static final int MAX_BIT_NR = 256;
	
	@Override
	public void execute(GameSystem arg0) {}

	@Override
	public void execute(GameSystem system, GameEvent arg1) {
		NetworkSequence lastSeq = null;
		EntityManagerNN nnMan = (EntityManagerNN) system.getEntityManager();
		NeuralNetworkComp nnComp = (NeuralNetworkComp) nnMan.getNNInformation().getComponent(NeuralNetworkComp.class);
		List<NetworkSequence> pop = nnComp.getPopulation();
		while(nnComp.getMaxPopSize() - nnComp.getPopulation().size() != 0) {
			System.out.println("[GeneticalSysNN::FillPopulation] Create random candidate...");
			lastSeq = generateRandomSequence(nnComp.getNetwork().getEdgeCount());
			pop.add(lastSeq);
		}
		if(nnComp.getNetwork().getSequence() == null) {
			nnComp.getNetwork().setSequence(nnComp.getPopulation().get(0)); 
		}
		system.getEventQueue().add(new StartGameEvent());
	}
	
	/**
	 * Generates a random network sequence
	 * @param edges
	 * @return
	 */
	private NetworkSequence generateRandomSequence(int edges) {
		String sequence = "";
		Random rand = new Random();
		for(int i = 0; i < edges; i++) {
			String edgeSeq = Integer.toBinaryString(rand.nextInt(MAX_BIT_NR));
			if(edgeSeq.length() < BIT_RANGE) {
				int zeroFillCount = BIT_RANGE-edgeSeq.length();
				for(int x = 0; x < zeroFillCount; x++) {
					edgeSeq = "0" + edgeSeq;
				}
			}
			sequence = sequence + edgeSeq;
		}
		return new NetworkSequence(sequence);
	}
}