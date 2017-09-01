package de.sloth.core.sysActivation.behavior;

import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.sysActivation.event.SystemActivationEvent;
import de.sloth.core.sysActivation.system.SystemActivationSystem;

public class BActivateSystem implements IBehavior {

	@Override
	public void execute(DefaultGameSystem system) {}

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent) {
		SystemActivationSystem gcSys = (SystemActivationSystem) system;
		SystemActivationEvent sae = (SystemActivationEvent) expectedEvent;
		for(DefaultGameSystem sys : gcSys.getCore().getRegistredSystems()) {
			if(sys.getSystemID().equals(sae.getTargetSystemID())) {
				sys.setActive(sae.isActive());
			}
		}
	}
}