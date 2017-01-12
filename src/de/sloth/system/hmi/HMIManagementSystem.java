package de.sloth.system.hmi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.FocusComp;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.event.HMIEvent;
import de.sloth.event.HMIInventoryEvent;
import de.sloth.event.HMIKeyword;
import de.sloth.hmi.GameHMI;
import de.sloth.hmi.InventoryGameLayer;
import de.sloth.hmi.MainMenuLayer;
import de.sloth.hmi.NotSupportedEntityException;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.hmi.WinGameLayer;
import de.sloth.hmi.LooseGameLayer;
import de.sloth.system.GameSystem;

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
					LooseGameLayer wlgl = (LooseGameLayer) mainHmi.getGameInterfaceLayer("wl");
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
								try {
									pil.setObservableEntity(entity);
								} catch (NotSupportedEntityException e) {
									e.printStackTrace();
								}
							}
						}
					}	
					delEvents.add(event);
				} else if (((HMIEvent) event).getHmiKeyword().equals(HMIKeyword.startGame)) {
					MainMenuLayer mml = (MainMenuLayer) mainHmi.getGameInterfaceLayer("main");
					PlayerInformationLayer pil = (PlayerInformationLayer) mainHmi.getGameInterfaceLayer("playerInfo");
					InventoryGameLayer igl = (InventoryGameLayer) mainHmi.getGameInterfaceLayer("igl");
					
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
								try {
									pil.setObservableEntity(entity);
									igl.setObservableEntity(entity);
								} catch (NotSupportedEntityException e) {
									e.printStackTrace();
								}
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
					WinGameLayer wlg = (WinGameLayer) mainHmi.getGameInterfaceLayer("wlg");
					pil.setVisible(false);
					pil.setDisable(true);
					wlg.setVisible(false);
					wlg.setDisable(true);
					mml.setVisible(true);
					mml.setDisable(false);
					mml.requestFocus();
					delEvents.add(event);
				} else if (((HMIEvent) event).getHmiKeyword().equals(HMIKeyword.showWin)) {
					PlayerInformationLayer pil = (PlayerInformationLayer) mainHmi.getGameInterfaceLayer("playerInfo");
					//MainMenuLayer mml = (MainMenuLayer) mainHmi.getGameInterfaceLayer("main");
					WinGameLayer wlg = (WinGameLayer) mainHmi.getGameInterfaceLayer("wlg");
					pil.setVisible(false);
					pil.setDisable(true);
					wlg.setVisible(true);
					wlg.setDisable(false);
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
				} else if(event instanceof HMIInventoryEvent) {
					InventoryGameLayer igl = (InventoryGameLayer) mainHmi.getGameInterfaceLayer("igl");
					igl.setActiveSlot(((HMIInventoryEvent) event).getPosX(), ((HMIInventoryEvent) event).getPosY());
					delEvents.add(event);
				}
			}
		}
		this.getEventQueue().removeAll(delEvents);
	}
}