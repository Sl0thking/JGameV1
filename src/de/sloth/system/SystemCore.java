package de.sloth.system;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import javafx.scene.canvas.GraphicsContext;

public class SystemCore extends GameSystem {

	GraphicsContext gc;
	AbstractRendererSystem cRenderer;

	public SystemCore(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue, GraphicsContext gc) {
		super(entities, eventQueue);
		this.gc = gc;
		this.cRenderer = new SpriteRendererSystem(entities, gc);
	}

	@Override
	public void run() {
		cRenderer.start();
		while(true) {}
	}
}