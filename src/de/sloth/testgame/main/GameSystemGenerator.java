package de.sloth.testgame.main;

import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import de.sloth.entity.Entity;
import de.sloth.hmi.HMICore;
import de.sloth.system.game.bgmSystem.PlaySong;
import de.sloth.system.game.collision.CollisionEvent;
import de.sloth.system.game.controlls.ControllSystem;
import de.sloth.system.game.core.GameCore;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IEntityManagement;
import de.sloth.system.game.systemActivation.ActivateAllSystems;
import de.sloth.system.game.systemActivation.ActivateSystem;
import de.sloth.system.game.systemActivation.GameCoreSystem;
import de.sloth.system.game.systemActivation.SystemActivationEvent;
import de.sloth.system.hmi.HMIGameSystem;


public class GameSystemGenerator {

	private static GameSystemGenerator instance;
	private static int maxX;
	private static int maxY;
	
	public static GameSystemGenerator getInstance() {
		if(instance == null) {
			instance = new GameSystemGenerator();
		}
		return instance;
	}
	
	private GameSystemGenerator() {
		GameSystemGenerator.maxX = (int) Screen.getPrimary().getBounds().getWidth();
		GameSystemGenerator.maxY = (int) Screen.getPrimary().getBounds().getHeight(); 
		
	}
	

	
	public GameSystem generateStartGameSystem(IEntityManagement entityManager, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem startGameSystem = new GameSystem("startSys", StartGameEvent.class, entityManager, eventQueue);
		startGameSystem.registerBehavior("Any", new StartGame());
		return startGameSystem;
	}
	
	public GameSystem generateMoveSystem(IEntityManagement entityManager, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem moveSystem = new GameSystem("moveSys", MoveEvent.class, entityManager, eventQueue);
		moveSystem.registerBehavior("Any", new Move(15, maxX, maxY));
		return moveSystem;
	}
	


	public GameSystem generateRenderSystem(IEntityManagement entityManager, HMICore gameHMI, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		HMIGameSystem renderSystem = new HMIGameSystem(gameHMI, "renderSys", null, entityManager, eventQueue);
		renderSystem.setActive(true);
		renderSystem.registerBehavior("Any", new Render(maxX, maxY));
		return renderSystem;
	}


	
	public GameSystem generateSystemActivationSystem(IEntityManagement entityManager, GameCore core, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameCoreSystem systemActivationSystem = new GameCoreSystem("sysActiveSys", SystemActivationEvent.class, entityManager, eventQueue, core);
		systemActivationSystem.registerBehavior("single", new ActivateSystem());
		systemActivationSystem.registerBehavior("all", new ActivateAllSystems());
		return systemActivationSystem;
	}
	
	public GameSystem generateBGMSystem(IEntityManagement entityManager, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem bgmSystem = new GameSystem("bgmSys", null, entityManager, eventQueue);
		bgmSystem.registerBehavior("Any", new PlaySong());
		bgmSystem.setActive(false);
		return bgmSystem;
	}

	public EventHandler<? super KeyEvent> generateGameControllSystem(
			IEntityManagement entityManager,
			ConcurrentLinkedQueue<GameEvent> eventQueue) {
		ControllSystem cSys = new ControllSystem("cSys", entityManager, eventQueue);
		cSys.registerKey(KeyCode.A, new MoveEvent(Direction.LEFT));
		cSys.registerKey(KeyCode.D, new MoveEvent(Direction.RIGHT));
		return cSys;
	}
}