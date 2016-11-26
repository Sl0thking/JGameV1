package de.sloth.system;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.FocusComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import javafx.scene.canvas.GraphicsContext;

public class SpriteRendererSystem extends AbstractRendererSystem {
	
	public SpriteRendererSystem(ConcurrentLinkedQueue<Entity> entities, GraphicsContext gc) {
		super(entities, gc);
	}

	@Override
	public void handle(long now) {
		int midX = 7*40;
		int midY = 7*35;
		int transX = 0;
		int transY = 0;
		for(Entity entity : this.getEntities()) {
			FocusComp focusComp = (FocusComp) entity.getComponent(FocusComp.class);
			if(focusComp != null) {
				Position3DComp canvasMidComp = (Position3DComp) entity.getComponent(Position3DComp.class);
				transX = midX - canvasMidComp.getX();
				transY = midY - canvasMidComp.getY();
				break;
			}
		}
		this.getGc().clearRect(0, 0, this.getGc().getCanvas().getWidth(), this.getGc().getCanvas().getHeight());
		this.getGc().fillRect(0, 0, this.getGc().getCanvas().getWidth(), this.getGc().getCanvas().getHeight());
		for(Entity entity : this.getEntities()) {
			Position3DComp comp = (Position3DComp) entity.getComponent(Position3DComp.class);
			SpriteComp sprite = (SpriteComp) entity.getComponent(SpriteComp.class);
			int transformedPosX = comp.getX() + transX;
			int transformedPosY = comp.getY() + transY;
			if(comp != null && sprite != null &&
			   transformedPosX >= 0 && transformedPosX < 16*40 &&
			   transformedPosY >= 0 && transformedPosY < 16*35) {
				this.getGc().drawImage(sprite.getSprite(), transformedPosX, transformedPosY);
			}
		}
		try {
			Thread.sleep(1000/60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}