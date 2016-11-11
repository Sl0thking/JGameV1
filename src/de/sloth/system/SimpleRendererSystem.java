package de.sloth.system;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.Position2DComp;
import de.sloth.entity.Entity;
import javafx.scene.canvas.GraphicsContext;

public class SimpleRendererSystem extends AbstractRendererSystem {
	
	public SimpleRendererSystem(ConcurrentLinkedQueue<Entity> entities, GraphicsContext gc) {
		super(entities, gc);
	}

	@Override
	public void handle(long now) {
		this.getGc().clearRect(0, 0, this.getGc().getCanvas().getWidth(), this.getGc().getCanvas().getHeight());
		for(Entity entity : this.getEntities()) {
			Position2DComp comp = (Position2DComp) entity.getComponent(Position2DComp.class);
			this.getGc().fillOval(comp.getX(), comp.getY(), 32, 32);
		}
		try {
			Thread.sleep(1000/60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}