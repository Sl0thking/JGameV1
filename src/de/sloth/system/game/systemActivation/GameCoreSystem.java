package de.sloth.system.game.systemActivation;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.system.game.core.ConfigLoader;
import de.sloth.system.game.core.GameCore;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IEntityManagement;

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