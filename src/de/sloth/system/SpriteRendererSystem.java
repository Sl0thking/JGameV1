package de.sloth.system;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import javafx.scene.canvas.GraphicsContext;

public class SpriteRendererSystem extends AbstractRendererSystem {
	
	public SpriteRendererSystem(ConcurrentLinkedQueue<Entity> entities, GraphicsContext gc) {
		super(entities, gc);
	}

	@Override
	public void executeSystem() {
		this.getGc().clearRect(0, 0, this.getGc().getCanvas().getWidth(), this.getGc().getCanvas().getHeight());
		this.getGc().fillRect(0, 0, this.getGc().getCanvas().getWidth(), this.getGc().getCanvas().getHeight());
		//this.getGc().drawImage(new Image("file:./sprites/background.png"), 0, 0);
		for(Entity entity : this.getEntities()) {
			Position3DComp comp = (Position3DComp) entity.getComponent(Position3DComp.class);
			SpriteComp sprite = (SpriteComp) entity.getComponent(SpriteComp.class);
			if(comp != null && sprite != null) {
				this.getGc().drawImage(sprite.getSprite(), comp.getX(), comp.getY());
			}
		}
	}
}