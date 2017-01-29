package de.sloth.generators;

import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.stage.Screen;
import de.sloth.entity.Entity;
import de.sloth.hmi.GameHMI;
import de.sloth.systemv2.battle.Battle;
import de.sloth.systemv2.battle.BattleEvent;
import de.sloth.systemv2.collision.Collision;
import de.sloth.systemv2.collision.CollisionEvent;
import de.sloth.systemv2.core.GameEvent;
import de.sloth.systemv2.core.GameSystem;
import de.sloth.systemv2.endConditions.EndCondition;
import de.sloth.systemv2.hmi.HMIGameSystem;
import de.sloth.systemv2.hmi.Render;
import de.sloth.systemv2.hmi.hmiMenu.HMIMenuEvent;
import de.sloth.systemv2.hmi.hmiMenu.ShowGame;
import de.sloth.systemv2.hmi.hmiMenu.ShowMenu;
import de.sloth.systemv2.hmi.hmiMenu.ToggleGameOver;
import de.sloth.systemv2.inventory.ChangeCursor;
import de.sloth.systemv2.inventory.CollectItem;
import de.sloth.systemv2.inventory.InventoryEvent;
import de.sloth.systemv2.inventory.UseItem;
import de.sloth.systemv2.move.Move;
import de.sloth.systemv2.move.MoveEvent;
import de.sloth.systemv2.startGame.StartGame;
import de.sloth.systemv2.startGame.StartGameEvent;

public class GameSystemGenerator {

	private static GameSystemGenerator instance;
	private static int maxX;
	private static int maxY;
	
	public static GameSystemGenerator getInsance() {
		if(instance == null) {
			instance = new GameSystemGenerator();
		}
		return instance;
	}
	
	private GameSystemGenerator() {
		this.maxX = (int) Screen.getPrimary().getBounds().getWidth();
		this.maxY = (int) Screen.getPrimary().getBounds().getHeight(); 
		
	}
	
	public GameSystem generateBattleSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem battleSystem = new GameSystem("battleSys", BattleEvent.class, entities, eventQueue);
		battleSystem.registerBehavior("Any", new Battle());
		return battleSystem;
	}
	
	public GameSystem generateEndConditionSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem endConditionSystem = new GameSystem("ecSys", null, entities, eventQueue);
		endConditionSystem.registerBehavior("Any", new EndCondition());
		return endConditionSystem;
	}
	
	public GameSystem generateInventorySystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem inventorySystem = new GameSystem("inventorySys", InventoryEvent.class, entities, eventQueue);
		inventorySystem.registerBehavior("collectItem", new CollectItem());
		inventorySystem.registerBehavior("useItem", new UseItem());
		inventorySystem.registerBehavior("changeCursor", new ChangeCursor());
		return inventorySystem;
	}
	
	public GameSystem generateStartGameSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem startGameSystem = new GameSystem("startSys", StartGameEvent.class, entities, eventQueue);
		startGameSystem.registerBehavior("Any", new StartGame());
		return startGameSystem;
	}
	
	public GameSystem generateMoveSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem moveSystem = new GameSystem("moveSys", MoveEvent.class, entities, eventQueue);
		moveSystem.registerBehavior("Any", new Move(maxX, maxY));
		return moveSystem;
	}
	
	public GameSystem generateCollisionSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem collisionSystem = new GameSystem("collisionSys", CollisionEvent.class, entities, eventQueue);
		collisionSystem.registerBehavior("Any", new Collision());
		return collisionSystem;
	}

	public GameSystem generateRenderSystem(GameHMI gameHMI, ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		HMIGameSystem renderSystem = new HMIGameSystem(gameHMI, "renderSys", null, entities, eventQueue);
		renderSystem.registerBehavior("Any", new Render(maxX, maxY));
		return renderSystem;
	}

	public GameSystem generateHMIMenuSystem(GameHMI gameHMI, ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		HMIGameSystem hmiMenuSystem = new HMIGameSystem(gameHMI, "hmiMenuSys", HMIMenuEvent.class, entities, eventQueue);
		hmiMenuSystem.registerBehavior("toggleGameOver", new ToggleGameOver());
		hmiMenuSystem.registerBehavior("showGame", new ShowGame());
		hmiMenuSystem.registerBehavior("showMenu", new ShowMenu());
		return hmiMenuSystem;
	}
}
