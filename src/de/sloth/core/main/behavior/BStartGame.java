package de.sloth.core.main.behavior;

import java.util.Random;

import de.sloth.core.main.component.HealthComp;
import de.sloth.core.main.component.ScoreComp;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.loader.ConfigLoader;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.movement.event.Direction;
import de.sloth.core.sysActivation.event.SystemActivationEvent;
import de.sloth.hmi.system.HMIGameSystem;
import de.sloth.tba.spears.component.SpearBagComp;
/**
 * Behavior for StartGameSystem. Executes necessary operations
 * to prepare a new game.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 18.05.2017
 *
 */
public class BStartGame implements IBehavior {

	@Override
	public void execute(DefaultGameSystem system) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(DefaultGameSystem system, GameEvent expectedEvent)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	/*
	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		System.out.println("[StartGameSys::StartGame] Start game round...");
		system.getEntityManager().clear();
		Entity player = EntityGenerator.getInstance().generatePlayer();
		HMIGameSystem hmiSystem = (HMIGameSystem) system;
		//PlayerStatusLayer psl = (PlayerStatusLayer) hmiSystem.getGameHMI().getGameInterfaceLayer("psl");
			
		HealthComp heComp = (HealthComp) player.getComponent(HealthComp.class);
		SpearBagComp spComp = (SpearBagComp) player.getComponent(SpearBagComp.class);
		ScoreComp scoreComp = (ScoreComp) player.getComponent(ScoreComp.class);
		psl.getLifeProperty().bind(heComp.getLifeProperty());
		psl.getSpearProperty().bind(spComp.getSpearProperty());
		psl.getScoreLabel().textProperty().bind(new SimpleStringProperty("Ruhm ").concat(scoreComp.getScoreProperty().asString()));
		
		system.getEntityManager().addEntity(player);
		
		int enemyCount = Integer.parseInt(ConfigLoader.getInstance().getConfig("enemyCount", "3"));
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
		
	}*/
}