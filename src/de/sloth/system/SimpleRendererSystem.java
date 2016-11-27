package de.sloth.system;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.Position3DComp;
import de.sloth.component.SimpleGraphicComp;
import de.sloth.entity.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimpleRendererSystem extends AbstractRendererSystem {
	
	public SimpleRendererSystem(ConcurrentLinkedQueue<Entity> entities, GraphicsContext gc) {
		super(entities, gc);
	}

	@Override
	public void executeSystem() {
		this.getGc().clearRect(0, 0, this.getGc().getCanvas().getWidth(), this.getGc().getCanvas().getHeight());
		for(Entity entity : this.getEntities()) {
			Position3DComp comp = (Position3DComp) entity.getComponent(Position3DComp.class);
			SimpleGraphicComp sprite = (SimpleGraphicComp) entity.getComponent(SimpleGraphicComp.class);
			if(comp != null && sprite != null) {
				if(sprite.getSimpleIdent().equals("playable")) {
					this.getGc().setFill(Color.BLACK);
					this.getGc().fillOval(comp.getX(), comp.getY(), 32, 32);
				} else if(sprite.getSimpleIdent().equals("enemy")) {
					this.getGc().setFill(Color.RED);
					this.getGc().fillOval(comp.getX(), comp.getY(), 32, 32);
				} else if(sprite.getSimpleIdent().equals("wall")) {
					this.getGc().setFill(Color.GREY);
					this.getGc().fillRect(comp.getX(), comp.getY(), 32, 32);
				}
			}
		}
	}
}