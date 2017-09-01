package de.sloth.core.main.behavior;

import de.sloth.core.main.component.HealthComp;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;



/**
 * Checks if player died and exit system.
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 18.05.2017
 */
public class BCheckForDeath implements IBehavior {
	
	@Override
	public void execute(DefaultGameSystem system) {
		Entity player = system.getEntityManager().getActivePlayabaleEntity();
		HealthComp hComp = (HealthComp) player.getComponent(HealthComp.class);
		if(hComp.getLifes() == 0) {
			System.exit(0);
		}
	}

	@Override
	public void execute(DefaultGameSystem arg0, GameEvent arg1) {}
}