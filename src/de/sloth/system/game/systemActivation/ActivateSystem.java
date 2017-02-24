package de.sloth.system.game.systemActivation;

import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class ActivateSystem implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		System.out.println("running...");
		GameCoreSystem gcSys = (GameCoreSystem) system;
		SystemActivationEvent sae = (SystemActivationEvent) expectedEvent;
		System.out.println(sae);
		for(GameSystem sys : gcSys.getCore().getRegistredSystems()) {
			System.out.println(sys.getSystemID() + " / " + sae.getTargetSystemID());
			if(sys.getSystemID().equals(sae.getTargetSystemID())) {
				sys.setActive(sae.isActive());
			}
		}
	}
}