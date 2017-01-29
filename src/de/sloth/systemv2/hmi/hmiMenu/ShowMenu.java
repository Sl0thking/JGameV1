package de.sloth.systemv2.hmi.hmiMenu;

import java.util.ArrayList;
import java.util.List;

import de.sloth.hmi.MainMenuLayer;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.hmi.WinGameLayer;
import de.sloth.systemv2.core.GameEvent;
import de.sloth.systemv2.core.GameSystem;
import de.sloth.systemv2.core.IBehavior;
import de.sloth.systemv2.hmi.HMIGameSystem;

public class ShowMenu implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		List<GameEvent> delEvents = new ArrayList<GameEvent>();
		HMIGameSystem hmiSys = (HMIGameSystem) system;
		PlayerInformationLayer pil = (PlayerInformationLayer) hmiSys.getGameHMI().getGameInterfaceLayer("playerInfo");
		MainMenuLayer mml = (MainMenuLayer) hmiSys.getGameHMI().getGameInterfaceLayer("main");
		WinGameLayer wlg = (WinGameLayer) hmiSys.getGameHMI().getGameInterfaceLayer("wlg");
		pil.setVisible(false);
		pil.setDisable(true);
		wlg.setVisible(false);
		wlg.setDisable(true);
		mml.setVisible(true);
		mml.setDisable(false);
		mml.requestFocus();
		delEvents.add(expectedEvent);
	}
}