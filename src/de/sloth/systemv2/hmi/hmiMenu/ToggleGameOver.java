package de.sloth.systemv2.hmi.hmiMenu;

import java.util.ArrayList;
import java.util.List;

import de.sloth.component.FocusComp;
import de.sloth.entity.Entity;
import de.sloth.hmi.LooseGameLayer;
import de.sloth.hmi.NotSupportedEntityException;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.systemv2.core.GameEvent;
import de.sloth.systemv2.core.GameSystem;
import de.sloth.systemv2.core.IBehavior;
import de.sloth.systemv2.hmi.HMIGameSystem;

public class ToggleGameOver implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		/*List<GameEvent> delEvents = new ArrayList<GameEvent>();
		HMIGameSystem hmiSys = (HMIGameSystem) system;
		LooseGameLayer wlgl = (LooseGameLayer) hmiSys.getGameHMI().getGameInterfaceLayer("wl");
		PlayerInformationLayer pil = (PlayerInformationLayer) hmiSys.getGameHMI().getGameInterfaceLayer("playerInfo");
		boolean isRestarted = pil.isDisabled();
		pil.setVisible(!pil.isVisible());
		pil.setDisable(!pil.isDisabled());
		pil.requestFocus();
		wlgl.setVisible(!wlgl.isVisible());
		wlgl.setDisable(!wlgl.isDisabled());
		if(isRestarted) {
			for(Entity entity: system.getEntities()) {
				FocusComp fComp = (FocusComp) entity.getComponent(FocusComp.class);
				if(fComp != null) {
					try {
						pil.setObservableEntity(entity);
					} catch (NotSupportedEntityException e) {
						e.printStackTrace();
					}
				}
			}
		}	
		system.getEventQueue().removeAll(delEvents);*/
	}
}