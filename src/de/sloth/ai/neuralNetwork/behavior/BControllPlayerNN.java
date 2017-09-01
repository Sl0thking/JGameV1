package de.sloth.ai.neuralNetwork.behavior;

import java.util.List;

import de.sloth.core.controll.event.PossibleMoveEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.loader.ConfigLoader;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.main.system.IEntityManagement;
import de.sloth.core.movement.event.Direction;
import de.sloth.ai.neuralNetwork.component.NeuralNetworkComp;
import de.sloth.ai.neuralNetwork.component.datatype.INeuralNetwork;
import de.sloth.ai.neuralNetwork.main.EntityManagerNN;
import de.sloth.ai.neuralNetwork.main.EntityNNInputConverter;
import de.sloth.tba.spears.component.FlyingComp;
import de.sloth.tba.spears.component.SpearBagComp;
import de.sloth.tba.spears.event.ThrowSpearEvent;

/**
 * Behavior for control of playable entity in neural network mode.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 20.05.2017
 *
 */
public class BControllPlayerNN implements IBehavior {

	@Override
	public void execute(DefaultGameSystem system) {
		/*EntityManagerNN nnMan = (EntityManagerNN) system.getEntityManager();
		INeuralNetwork nn = ((NeuralNetworkComp) nnMan.getNNInformation().getComponent(NeuralNetworkComp.class)).getNetwork();
		Entity player = system.getEntityManager().getActivePlayabaleEntity();
		int maxEnemies = Integer.valueOf(ConfigLoader.getInstance().getConfig("maxEnemies", "7"));
		int maxSpears = Integer.valueOf(ConfigLoader.getInstance().getConfig("maxSpears", "7"));
		//List<Entity> enemies = IEntityManagement.filterEntitiesByComponent(system.getEntityManager().getAllEntities(), VikingEnemyComp.class);
		List<Entity> spears = IEntityManagement.filterEntitiesByComponent(system.getEntityManager().getAllEntities(), FlyingComp.class);
		if(player != null) {
			SpearBagComp spComp = (SpearBagComp) player.getComponent(SpearBagComp.class);
			//check environ and give network input of entities
			double commandValue;
			try {
				//System.out.println(((NeuralNetwork) nn).getGraph().toStringNodeType(NodeType.INPUT, false));
				nn.setInputOfNode(EntityNNInputConverter.convertEntityToValue(player), "n_1");
				for(int i = 0; i < maxEnemies; i++) {
					if(i < enemies.size()) {
						nn.setInputOfNode(EntityNNInputConverter.convertEntityToValue(enemies.get(i)), "n_" + (i+2));
					} else {
						nn.setInputOfNode(0.0, "n_" + (i+2));
					}
				}
				for(int i = 0; i < maxSpears; i++) {
					if(i < spears.size()) {
						nn.setInputOfNode(EntityNNInputConverter.convertEntityToValue(spears.get(i)), "n_" + (i+5));
					} else {
						nn.setInputOfNode(0.0, "n_" + (i+5));
					}
				}
				commandValue = nn.processInput();
			} catch (Exception e) {
				e.printStackTrace();
				commandValue = 1.0;
			}
			if(commandValue <= 0.45) {
				system.getEventQueue().add(new PossibleMoveEvent(Direction.LEFT));
			} else if(commandValue > 0.45 && commandValue <= 0.55 && spComp.getSpears() > 0) {
				system.getEventQueue().add(new ThrowSpearEvent());
			} else {
				system.getEventQueue().add(new PossibleMoveEvent(Direction.RIGHT));
			}
		} */
	}

	@Override
	public void execute(DefaultGameSystem arg0, GameEvent arg1) {}
}