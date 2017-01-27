package de.sloth.system.game.renderer;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.hmi.LayeredCanvas;
import de.sloth.hmi.TripleBufferCanvas;
import de.sloth.system.GameSystem;

public abstract class AbstractRendererSystem extends GameSystem{
	private LayeredCanvas lc;
	
	public AbstractRendererSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue, LayeredCanvas lc) {
		super(entities, eventQueue);
		this.lc = lc;
	}
	
	public LayeredCanvas getLc() {
		return this.lc;
	}

	public TripleBufferCanvas getGcOfLayer(int z) {
		return this.lc.getLayer(z);
	}
}
