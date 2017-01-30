package de.sloth.system.hmi;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.hmi.GameHMI;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;

public class HMIGameSystem extends GameSystem {

	private GameHMI gameHMI;
	
	public HMIGameSystem(GameHMI gameHMI, String systemID) {
		super(systemID);
		this.setGameHMI(gameHMI);
	}

	public HMIGameSystem(GameHMI gameHMI, String systemID,
			Class<? extends GameEvent> listeningEvent,
			ConcurrentLinkedQueue<Entity> entities,
			ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(systemID, listeningEvent, entities, eventQueue);
		this.setGameHMI(gameHMI);
	}

	public GameHMI getGameHMI() {
		return gameHMI;
	}

	public void setGameHMI(GameHMI gameHMI) {
		this.gameHMI = gameHMI;
	}
}