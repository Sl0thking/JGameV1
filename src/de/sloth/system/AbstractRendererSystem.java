package de.sloth.system;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.hmi.LayeredFieldCanvasPane;
import javafx.scene.canvas.GraphicsContext;

public abstract class AbstractRendererSystem extends GameSystem{
	private ConcurrentLinkedQueue<Entity> entities;
	private LayeredFieldCanvasPane lfcp;
	
	public AbstractRendererSystem(ConcurrentLinkedQueue<Entity> entities, LayeredFieldCanvasPane lfcp) {
		this.entities = entities;
		this.lfcp = lfcp;
	}
	
	public ConcurrentLinkedQueue<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ConcurrentLinkedQueue<Entity> entities) {
		this.entities = entities;
	}

	public GraphicsContext getGc(int layer) {
		return lfcp.getGraphicContext(layer);
	}
	
	public LayeredFieldCanvasPane getLFCP() {
		return lfcp;
	}
}
