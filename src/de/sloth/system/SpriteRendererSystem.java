package de.sloth.system;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.FocusComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import de.sloth.hmi.LayeredFieldCanvasPane;
import javafx.scene.canvas.GraphicsContext;

public class SpriteRendererSystem extends AbstractRendererSystem {
	
	public SpriteRendererSystem(ConcurrentLinkedQueue<Entity> entities, LayeredFieldCanvasPane lfcp) {
		super(entities, lfcp);
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
		for(int i = 0; i < this.getLFCP().getLayers(); i++) {
			this.getGc(i).clearRect(0, 0, this.getGc(i).getCanvas().getWidth(), this.getGc(i).getCanvas().getHeight());
			if(i == 0) {
				this.getGc(i).fillRect(0, 0, this.getGc(i).getCanvas().getWidth(), this.getGc(i).getCanvas().getHeight());
			}
		}
		
		for(Entity entity : this.getEntities()) {
			Position3DComp comp = (Position3DComp) entity.getComponent(Position3DComp.class);
			SpriteComp sprite = (SpriteComp) entity.getComponent(SpriteComp.class);
			int transformedPosX = comp.getX() + transX;
			int transformedPosY = comp.getY() + transY;
			int z = comp.getZ();
			if(comp != null && sprite != null &&
			   transformedPosX >= 0 && transformedPosX < 1366 &&
			   transformedPosY >= 0 && transformedPosY < 768) {
				this.getGc(z).drawImage(sprite.getSprite(), transformedPosX, transformedPosY);
			}
		}
	}
}