package de.sloth.core.neuralNetwork.behavior;

import java.util.Random;

import de.sloth.core.main.ConfigLoader;
import de.sloth.core.main.Entity;
import de.sloth.core.main.EntityGenerator;
import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;
import de.sloth.core.movement.event.Direction;
import de.sloth.core.sysActivation.SystemActivationEvent;

/**
 * Behavior to start the game without the gui
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 22.05.2017
 *
 */
public class BStartGameWithoutGUI implements IBehavior {

	@Override
	public void execute(GameSystem arg0) {}

	@Override
	public void execute(GameSystem system, GameEvent arg1) {
		if(!system.isQuiet()) {
			System.out.println("[StartGameSys::StartGameWithoutGUI] Start game round...");
		}
		system.getEntityManager().clear();
		Entity player = EntityGenerator.getInstance().generatePlayer();
		system.getEntityManager().addEntity(player);
		int enemyCount = Integer.parseInt(ConfigLoader.getInstance().getConfig("enemyCount", "1"));
		Random rand = new Random();
		for(int i = 0; i < enemyCount; i++) {
			Direction direct = Direction.CENTER;
			int nDirection = rand.nextInt(3);
			if(nDirection == 0) {
				direct = Direction.LEFT;
			} else if(nDirection == 1) {
				direct = Direction.RIGHT;
			}
			system.getEntityManager().addEntity(EntityGenerator.getInstance().generateEnemy(direct));
		}
		system.getEventQueue().add(new SystemActivationEvent("single", "endCondition", true));
	}
}