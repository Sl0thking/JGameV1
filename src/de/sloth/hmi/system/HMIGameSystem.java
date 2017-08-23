package de.sloth.hmi.system;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IEntityManagement;
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
			ConcurrentLinkedQueue<GameEvent> eventQueue) {
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