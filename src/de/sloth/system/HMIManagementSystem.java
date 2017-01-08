package de.sloth.system;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.FocusComp;
import de.sloth.component.LivingComp;
import de.sloth.component.LvlComp;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.event.HMIEvent;
import de.sloth.event.HMIKeyword;
import de.sloth.hmi.GameHMI;
import de.sloth.hmi.InventoryGameLayer;
import de.sloth.hmi.MainMenuLayer;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.hmi.WinLooseGameLayer;

public class HMIManagementSystem extends GameSystem {

	private GameHMI mainHmi;
	
	public HMIManagementSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue, GameHMI mainHmi) {
		super(entities, eventQueue);
		this.mainHmi = mainHmi;
	}
	
	@Override
	public void executeSystem() {
		List<GameEvent> delEvents = new ArrayList<GameEvent>();
		for(GameEvent event:this.getEventQueue()) {
			if(event instanceof HMIEvent) {
				if (((HMIEvent) event).getHmiKeyword().equals(HMIKeyword.togglePlayerInfo)) {
					PlayerInformationLayer pil = (PlayerInformationLayer) mainHmi.getGameInterfaceLayer("playerInfo");
					pil.setVisible(!pil.isVisible());
					delEvents.add(event);
				} else if (((HMIEvent) event).getHmiKeyword().equals(HMIKeyword.toggleGameOver)) {
					WinLooseGameLayer wlgl = (WinLooseGameLayer) mainHmi.getGameInterfaceLayer("wl");
					PlayerInformationLayer pil = (PlayerInformationLayer) mainHmi.getGameInterfaceLayer("playerInfo");
					boolean isRestarted = pil.isDisabled();
					pil.setVisible(!pil.isVisible());
					pil.setDisable(!pil.isDisabled());
					pil.requestFocus();
					wlgl.setVisible(!wlgl.isVisible());
					wlgl.setDisable(!wlgl.isDisabled());
					if(isRestarted) {
						for(Entity entity: this.getEntities()) {
							FocusComp fComp = (FocusComp) entity.getComponent(FocusComp.class);
							if(fComp != null) {
								LivingComp lComp = (LivingComp) entity.getComponent(LivingComp.class);
								LvlComp lvlcomp = (LvlComp) entity.getComponent(LvlComp.class);
								pil.getEBar().getCurrValue().bind(lvlcomp.getCurrExp());
								pil.getEBar().getMaxValue().bind(lvlcomp.getMaxExp());
								pil.gethBar().getCurrValue().bind(lComp.getHpProperty());
								pil.gethBar().getMaxValue().bind(lComp.getHpMaxProperty());
							}
						}
					}	
					delEvents.add(event);
				} else if (((HMIEvent) event).getHmiKeyword().equals(HMIKeyword.startGame)) {
					MainMenuLayer mml = (MainMenuLayer) mainHmi.getGameInterfaceLayer("main");
					PlayerInformationLayer pil = (PlayerInformationLayer) mainHmi.getGameInterfaceLayer("playerInfo");
					boolean isRestarted = pil.isDisabled();
					pil.setVisible(!pil.isVisible());
					pil.setDisable(!pil.isDisabled());
					pil.requestFocus();
					mml.setVisible(!mml.isVisible());
					mml.setDisable(!mml.isDisabled());
					if(isRestarted) {
						for(Entity entity: this.getEntities()) {
							FocusComp fComp = (FocusComp) entity.getComponent(FocusComp.class);
							if(fComp != null) {
								LivingComp lComp = (LivingComp) entity.getComponent(LivingComp.class);
								LvlComp lvlcomp = (LvlComp) entity.getComponent(LvlComp.class);
								pil.getEBar().getCurrValue().bind(lvlcomp.getCurrExp());
								pil.getEBar().getMaxValue().bind(lvlcomp.getMaxExp());
								pil.gethBar().getCurrValue().bind(lComp.getHpProperty());
								pil.gethBar().getMaxValue().bind(lComp.getHpMaxProperty());
							}
						}
					}	
					delEvents.add(event);
				} else if (((HMIEvent) event).getHmiKeyword().equals(HMIKeyword.showInventory)) {
					PlayerInformationLayer pil = (PlayerInformationLayer) mainHmi.getGameInterfaceLayer("playerInfo");
					InventoryGameLayer igl = (InventoryGameLayer) mainHmi.getGameInterfaceLayer("igl");
					pil.setVisible(false);
					pil.setDisable(true);
					igl.setDisable(false);
					igl.setVisible(true);
					igl.requestFocus();
					delEvents.add(event);
				} else if (((HMIEvent) event).getHmiKeyword().equals(HMIKeyword.showMenu)) {
					PlayerInformationLayer pil = (PlayerInformationLayer) mainHmi.getGameInterfaceLayer("playerInfo");
					MainMenuLayer mml = (MainMenuLayer) mainHmi.getGameInterfaceLayer("main");
					pil.setVisible(false);
					pil.setDisable(true);
					mml.setVisible(true);
					mml.setDisable(false);
					mml.requestFocus();
					delEvents.add(event);
				} else if (((HMIEvent) event).getHmiKeyword().equals(HMIKeyword.closeInventory)) {
					PlayerInformationLayer pil = (PlayerInformationLayer) mainHmi.getGameInterfaceLayer("playerInfo");
					InventoryGameLayer igl = (InventoryGameLayer) mainHmi.getGameInterfaceLayer("igl");
					pil.setVisible(true);
					pil.setDisable(false);
					igl.setDisable(true);
					igl.setVisible(false);
					pil.requestFocus();
					delEvents.add(event);
				} 
			}
		}
		this.getEventQueue().removeAll(delEvents);
	}
}