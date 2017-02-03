package de.sloth.system.hmi;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.hmi.HMICore;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IEntityManagement;

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