package de.sloth.core.neuralNetwork;

import java.util.List;

import de.sloth.core.main.Entity;
import de.sloth.core.main.EntityManager;
import de.sloth.core.neuralNetwork.component.NeuralNetworkComp;

/**
 * Entity manager specialized for neural networks.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 18.05.2017
 *
 */
public class EntityManagerNN extends EntityManager {
	private Entity NNInformation;

	public EntityManagerNN() {
		super();
		NNInformation = null;
	}
	
	@Override
	public void addEntity(Entity entity) {
		if(entity.getComponent(NeuralNetworkComp.class) != null) {
			this.NNInformation = entity;
		} else {
			super.addEntity(entity);
		}
	}
	
	@Override
	public List<Entity> getAllEntities() {
		List<Entity> returningEntities = super.getAllEntities();
		returningEntities.add(NNInformation);
		return returningEntities;
	}

	public Entity getNNInformation() {
		return NNInformation;
	}

	public void setNNInformation(Entity nNInformation) {
		NNInformation = nNInformation;
	}
}