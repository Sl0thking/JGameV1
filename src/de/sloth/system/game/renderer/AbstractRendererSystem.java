package de.sloth.system.game.renderer;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.system.GameSystem;
import javafx.scene.canvas.GraphicsContext;

public abstract class AbstractRendererSystem extends GameSystem{
	private ConcurrentLinkedQueue<Entity> entities;
	private GraphicsContext gc;
	
	public AbstractRendererSystem(ConcurrentLinkedQueue<Entity> entities, GraphicsContext gc) {
		this.entities = entities;
		this.gc = gc;
	}
	
	public ConcurrentLinkedQueue<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ConcurrentLinkedQueue<Entity> entities) {
		this.entities = entities;
	}

	public GraphicsContext getGc() {
		return gc;
	}
}
