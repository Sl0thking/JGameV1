package de.sloth.core.sysActivation;

import java.util.List;

import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameCore;
import de.sloth.core.main.system.GameSystem;
import de.sloth.core.main.system.IEntityManagement;

/**
 * Extended GameSystem, to allow access to the core and therefore
 * his systems. 
 * 
 * @author Kevin Jolitz
 * @date 27.08.2017
 * @version 0.1.0
 *
 */
public class GameCoreSystem extends GameSystem {

	private GameCore core;
	
	public GameCoreSystem(String systemID, Class<? extends GameEvent> listeningEvent,
			IEntityManagement entityManager, List<GameEvent> eventQueue,
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