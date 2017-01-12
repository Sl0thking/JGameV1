package de.sloth.system.game.renderer;

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
	public void executeSystem() {
		int midX = (34*40) / 2;
		int midY = (21*35) / 2; 
		//(int) Math.round(this.getLFCP().getGraphicContext(0).getCanvas().getHeight() / 2);
		/* int midX = 7*40;
		int midY = 7*35; */
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
		
		//System.out.println(this.getGc());
		
		this.getGc().clearRect(0, 0, this.getGc().getCanvas().getWidth(), this.getGc().getCanvas().getHeight());
		this.getGc().fillRect(0, 0, this.getGc().getCanvas().getWidth(), this.getGc().getCanvas().getHeight());
	
		for(int i = 0; i < 3; i++) {
			for(Entity entity : this.getEntities()) {
				Position3DComp comp = (Position3DComp) entity.getComponent(Position3DComp.class);
				SpriteComp sprite = (SpriteComp) entity.getComponent(SpriteComp.class);
				int transformedPosX = comp.getX() + transX;
				int transformedPosY = comp.getY() + transY;
				int z = comp.getZ();
				if(z == i) {
					if(comp != null && sprite != null &&
						transformedPosX >= 0 && transformedPosX < 1366 &&
						transformedPosY >= 0 && transformedPosY < 768) {
						this.getGc().drawImage(sprite.getSprite(), transformedPosX, transformedPosY);
					}
				}
			}
		}
	}
}