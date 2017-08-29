package de.sloth.core.main.behavior;

import de.sloth.core.main.component.AnimationComp;
import de.sloth.core.main.component.SpriteComp;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;
import de.sloth.core.movement.component.MovableComp;
import de.sloth.core.movement.component.Position3DComp;
import de.sloth.hmi.system.HMIGameSystem;



/**
 * Behavior for RenderSystem. Renders Entities with
 * specific components.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 18.05.2017
 *
 */
public class BRender implements IBehavior {

	private int screenWidth;
	private int screenHeight;
	
	/**
	 * Constructor
	 * @param screenWidth Width of screen
	 * @param screenHeight Height of screen
	 */
	public BRender(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	@Override
	public void execute(GameSystem system) {
		HMIGameSystem hmiSys = (HMIGameSystem) system;
		hmiSys.getGameHMI().getCanvas().clear();
		for(Entity renderingEntity : hmiSys.getEntityManager().getAllEntities()) {
			Position3DComp comp = (Position3DComp) renderingEntity.getComponent(Position3DComp.class);
			SpriteComp sprite = (SpriteComp) renderingEntity.getComponent(SpriteComp.class);
			AnimationComp aniComp = (AnimationComp) renderingEntity.getComponent(AnimationComp.class);
			MovableComp mvComp = (MovableComp) renderingEntity.getComponent(MovableComp.class);
			if(comp != null && sprite != null) {
				int z_c = comp.getZ();
				int y_c = comp.getY();
				int x_c = comp.getX();
				int transformedPosY = screenHeight-y_c;
				int transformedPosX = x_c;
				if(transformedPosX >= 0 && transformedPosX < screenWidth &&
						transformedPosY >= 0 && transformedPosY < screenHeight) {
					if(aniComp != null) {
						hmiSys.getGameHMI().getCanvas().getLayer(z_c).drawSprite(sprite.getSpritePath() + "_" + mvComp.getDirection().toString().toLowerCase() + ".png_" + aniComp.getAnimationPhase() + "_" + aniComp.getPhaseNr(), transformedPosX, transformedPosY);
						if(aniComp.getTicksForAnimation() > -1) {
							aniComp.setTicksForAnimation(aniComp.getTicksForAnimation()-1);
						}
						
						if(aniComp.getTicksForAnimation() == 0) {
							aniComp.setAnimationPhase(aniComp.getStdAnimationPhase());
							aniComp.setTicksForNextPhase(15);
							aniComp.setTicksForAnimation(-1);
						}
						
						aniComp.setTicksForNextPhase(aniComp.getTicksForNextPhase()-1);
						if(aniComp.getTicksForNextPhase() <= 0) {
							aniComp.setPhaseNr(aniComp.getPhaseNr()+1);
							if(aniComp.getPhaseNr() > 3 ) {
								aniComp.setPhaseNr(0);
							} 
							aniComp.setTicksForNextPhase(15);
						}
					} else {
						hmiSys.getGameHMI().getCanvas().getLayer(z_c).drawSprite(sprite.getSpritePath(), transformedPosX, transformedPosY);
					}
				}
			}
		}
	}
	
	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {}
}