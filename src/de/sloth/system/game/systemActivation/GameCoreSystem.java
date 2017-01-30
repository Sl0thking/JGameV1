package de.sloth.system.game.systemActivation;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameCore;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;

public class GameCoreSystem extends GameSystem {

	private GameCore core;
	
	public GameCoreSystem(String systemID, Class<? extends GameEvent> listeningEvent,
			ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue,
			GameCore core) {
		super(systemID, listeningEvent, entities, eventQueue);
		this.core = core;
	}

	public GameCore getCore() {
		return core;
	}

	public void setCore(GameCore core) {
		this.core = core;
	}
}