package de.sloth.hmi.system;

import java.util.List;

import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;
import de.sloth.core.main.system.IEntityManagement;
import de.sloth.hmi.HMICore;

public class HMIGameSystem extends GameSystem {

	private HMICore gameHMI;
	
	public HMIGameSystem(HMICore gameHMI, String systemID, IEntityManagement entityManager) {
		super(systemID, entityManager);
		this.setGameHMI(gameHMI);
	}

	public HMIGameSystem(HMICore gameHMI, String systemID,
			Class<? extends GameEvent> listeningEvent,
			IEntityManagement entityManager,
			List<GameEvent> eventQueue) {
		super(systemID, listeningEvent, entityManager, eventQueue);
		this.setGameHMI(gameHMI);
	}

	public HMICore getGameHMI() {
		return gameHMI;
	}

	public void setGameHMI(HMICore gameHMI) {
		this.gameHMI = gameHMI;
	}
}