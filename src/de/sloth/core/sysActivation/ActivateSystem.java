package de.sloth.core.sysActivation;

import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;

public class ActivateSystem implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		GameCoreSystem gcSys = (GameCoreSystem) system;
		SystemActivationEvent sae = (SystemActivationEvent) expectedEvent;
		for(GameSystem sys : gcSys.getCore().getRegistredSystems()) {
			if(sys.getSystemID().equals(sae.getTargetSystemID())) {
				sys.setActive(sae.isActive());
			}
		}
	}
}