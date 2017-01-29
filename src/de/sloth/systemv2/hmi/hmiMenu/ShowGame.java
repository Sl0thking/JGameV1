package de.sloth.systemv2.hmi.hmiMenu;

import java.util.ArrayList;
import java.util.List;

import de.sloth.component.FocusComp;
import de.sloth.entity.Entity;
import de.sloth.hmi.InventoryGameLayer;
import de.sloth.hmi.MainMenuLayer;
import de.sloth.hmi.NotSupportedEntityException;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.systemv2.core.GameEvent;
import de.sloth.systemv2.core.GameSystem;
import de.sloth.systemv2.core.IBehavior;
import de.sloth.systemv2.hmi.HMIGameSystem;

public class ShowGame implements IBehavior {

	@Override
	public void execute(GameSystem system) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		List<GameEvent> delEvents = new ArrayList<GameEvent>();
		HMIGameSystem hmiSys = (HMIGameSystem) system;
		MainMenuLayer mml = (MainMenuLayer) hmiSys.getGameHMI().getGameInterfaceLayer("main");
		PlayerInformationLayer pil = (PlayerInformationLayer) hmiSys.getGameHMI().getGameInterfaceLayer("playerInfo");
		InventoryGameLayer igl = (InventoryGameLayer) hmiSys.getGameHMI().getGameInterfaceLayer("igl");
		
		boolean isRestarted = pil.isDisabled();
		pil.setVisible(!pil.isVisible());
		pil.setDisable(!pil.isDisabled());
		pil.requestFocus();
		mml.setVisible(!mml.isVisible());
		mml.setDisable(!mml.isDisabled());
		
		
		if(isRestarted) {
			for(Entity entity: system.getEntities()) {
				FocusComp fComp = (FocusComp) entity.getComponent(FocusComp.class);
				if(fComp != null) {
					try {
						pil.setObservableEntity(entity);
						igl.setObservableEntity(entity);
					} catch (NotSupportedEntityException e) {
						e.printStackTrace();
					}
				}
			}
		}	
		delEvents.add(expectedEvent);
		system.getEventQueue().removeAll(delEvents);
	}
}