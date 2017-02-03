package de.sloth.testgame.main;

import java.util.List;

import de.sloth.component.FocusComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.game.core.IEntityManagement;
import de.sloth.system.hmi.HMIGameSystem;

public class Render implements IBehavior {

	private int screenWidth;
	private int screenHeight;
	
	public Render(int screenWidth, int screenHeight) {
		super();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	@Override
	public void execute(GameSystem system) {
		HMIGameSystem hmiSys = (HMIGameSystem) system;
		
		int midX = (int) Math.ceil(screenWidth / 2.0 / 32.0);
		int midY = (int) Math.ceil((screenHeight / 2.0) / 32.0); 
		int transX = 0;
		int transY = 0;
		
		hmiSys.getGameHMI().getCanvas().clear();
		
		List<Entity> entities = IEntityManagement.filterEntitiesByComponent(system.getEntityManager().getAllEntities(), FocusComp.class);
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
			
			for(Entity renderingEntity : hmiSys.getEntityManager().getAllEntities()) {
				Position3DComp comp = (Position3DComp) renderingEntity.getComponent(Position3DComp.class);
				SpriteComp sprite = (SpriteComp) renderingEntity.getComponent(SpriteComp.class);
				//int transformedPosX = (comp.getX() + transX)*32;
				//int transformedPosY = (comp.getY() + transY)*32;
				int z_c = comp.getZ();
				int y_c = comp.getY();
				int x_c = comp.getX();
				int transformedPosY = screenHeight-y_c-64;
				int transformedPosX = x_c;
				if(transformedPosX >= 0 && transformedPosX < screenWidth &&
				   transformedPosY >= 0 && transformedPosY < screenHeight) {
						hmiSys.getGameHMI().getCanvas().getLayer(z_c).drawSprite(sprite.getSpritePath(), transformedPosX, transformedPosY);
				}
			}
		}
	}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {}
}
