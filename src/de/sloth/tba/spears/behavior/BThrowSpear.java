package de.sloth.tba.spears.behavior;

import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.loader.ConfigLoader;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.main.system.IEntityManagement;
import de.sloth.tba.spears.component.FlyingComp;
import de.sloth.tba.spears.component.SpearBagComp;
import de.sloth.tba.spears.event.ThrowSpearEvent;

/**
 * Behavior for a system which works with spears.
 * Checks if a ThrowSpearEvent is triggered,
 * the environment is right and spawns a spear
 * when everything is alright.
 * 
 * [Engine candidate]
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 16.05.2017
 */
public class BThrowSpear implements IBehavior {

	@Override
	public void execute(DefaultGameSystem system) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	/*
	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		ThrowSpearEvent tse = (ThrowSpearEvent) expectedEvent;
		int spearsInGame = IEntityManagement.filterEntitiesByComponent(system.getEntityManager().getAllEntities(), FlyingComp.class).size();
		if(spearsInGame < Integer.valueOf(ConfigLoader.getInstance().getConfig("maxSpears", "18"))) {
			if(tse.getThrowingEntity() == null) {
				Entity sloth = system.getEntityManager().getActivePlayabaleEntity();
				SpearBagComp scomp = (SpearBagComp) sloth.getComponent(SpearBagComp.class);
				if(scomp.getSpears() > 0) {
					system.getEntityManager().addEntity(EntityGenerator.getInstance().generateFlyingSpear(sloth));
					scomp.setSpears(scomp.getSpears()-1);
				}
			} else {
				system.getEntityManager().addEntity(EntityGenerator.getInstance().generateFlyingSpear(tse.getThrowingEntity()));
			}
		}
	} */
}