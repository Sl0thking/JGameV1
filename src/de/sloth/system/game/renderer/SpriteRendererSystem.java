package de.sloth.system.game.renderer;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.FocusComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.hmi.LayeredCanvas;

public class SpriteRendererSystem extends AbstractRendererSystem {
	private int screenHeight;
	private int screenWidth;
	
	public SpriteRendererSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue, LayeredCanvas lc, int screenWidth, int screenHeight) {
		super(entities, eventQueue, lc);
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}

	@Override
	public void executeSystem() {
		int midX = (int) Math.ceil(screenWidth / 2.0 / 64.0);
		int midY = (int) Math.ceil((screenHeight / 2.0) / 32.0); 
		int transX = 0;
		int transY = 0;
		/*int minX = 0;
		int minY = 0;
		int maxX = 0;
		int maxY = 0; */
		
		this.getLc().clear();
		
		List<Entity> entities = this.filterEntitiesByComponent(FocusComp.class);
		Entity entity;
		if(entities.size() == 0) {
			entity = null;
		} else {
			entity = entities.get(0);
		}
		if(entity != null) {
			Position3DComp canvasMidComp = (Position3DComp) entity.getComponent(Position3DComp.class);
			transX = midX - canvasMidComp.getX();
			transY = midY - canvasMidComp.getY();
			/*minX = canvasMidComp.getX()-4;
			maxX = canvasMidComp.getX()+4;
			minY = canvasMidComp.getY()-4;
			maxY = canvasMidComp.getY()+4; */
			
			for(Entity renderingEntity : this.getEntities()) {
				Position3DComp comp = (Position3DComp) renderingEntity.getComponent(Position3DComp.class);
				SpriteComp sprite = (SpriteComp) renderingEntity.getComponent(SpriteComp.class);
				int transformedPosX = (comp.getX() + transX)*64;
				int transformedPosY = (comp.getY() + transY)*32;
					
				//int x_c = comp.getX();
				int z_c = comp.getZ();
				//int y_c = comp.getY();
				if(transformedPosX >= 0 && transformedPosX < screenWidth &&
				   transformedPosY >= 0 && transformedPosY < screenHeight) {// &&
				   //(x_c > minX && x_c < maxX && y_c > minY && y_c < maxY)) {
						this.getGcOfLayer(z_c).drawSprite(sprite.getSpritePath(), transformedPosX, transformedPosY);
				}
			}
		}
	}
}