package de.sloth.core.main.behavior;

import java.util.List;
import java.util.Random;





import de.sloth.core.main.ConfigLoader;
import de.sloth.core.main.Entity;
import de.sloth.core.main.EntityGenerator;
import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;
import de.sloth.core.main.IEntityManagement;
import de.sloth.core.main.VikingEnemyComp;
import de.sloth.core.movement.event.Direction;
import de.sloth.core.movement.event.PossibleMoveEvent;
import de.sloth.core.spears.event.ThrowSpearEvent;



/**
 * Behavior to controll enemies on the field.
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 18.05.2017
 *
 */
public class BControllEnemy implements IBehavior {

	@Override
	public void execute(GameSystem system) {
		Random rand = new Random();
		List<Entity> enemies = IEntityManagement.filterEntitiesByComponent(system.getEntityManager().getAllEntities(), VikingEnemyComp.class);
		if(enemies.size() < Integer.valueOf(ConfigLoader.getInstance().getConfig("maxEnemies", "7"))) {
			int nenemy = rand.nextInt(200);
			if(nenemy == 0) {
				Direction direct = Direction.CENTER;
				int nDirection = rand.nextInt(3);
				if(nDirection == 0) {
					direct = Direction.LEFT;
				} else if(nDirection == 1) {
					direct = Direction.RIGHT;
				}
				system.getEntityManager().addEntity(EntityGenerator.getInstance().generateEnemy(direct));
			} 
		}
		
		for(Entity enemy : enemies) {
			VikingEnemyComp seo = (VikingEnemyComp) enemy.getComponent(VikingEnemyComp.class);
			if(seo.getCurrTickDelay() == 0) {
				Direction direct = Direction.RIGHT;
				boolean isLeft = rand.nextBoolean();
				if(isLeft) {
					direct = Direction.LEFT;
				}
				system.getEventQueue().add(new PossibleMoveEvent(enemy, direct));
				int throwSpear = rand.nextInt(35);
				if(throwSpear == 0) {
					system.getEventQueue().add(new ThrowSpearEvent(enemy));
				}
				seo.setCurrTickDelay(seo.getTickDelay());
			} else {
				seo.setCurrTickDelay(seo.getCurrTickDelay()-1);
			}
		}
	}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {}
}