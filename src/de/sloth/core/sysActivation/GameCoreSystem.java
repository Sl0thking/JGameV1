package de.sloth.core.sysActivation;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.core.main.ConfigLoader;
import de.sloth.core.main.GameCore;
import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IEntityManagement;

public class GameCoreSystem extends GameSystem {

	private GameCore core;
	
	public GameCoreSystem(String systemID, Class<? extends GameEvent> listeningEvent,
			IEntityManagement entityManager, ConcurrentLinkedQueue<GameEvent> eventQueue,
			GameCore core) {
		super(systemID, listeningEvent, entityManager, eventQueue);
		this.core = core;
	}

	public GameCore getCore() {
		return core;
	}

	public void setCore(GameCore core) {
		this.core = core;
	}
}