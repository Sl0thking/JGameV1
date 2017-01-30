package de.sloth.generators;

import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.stage.Screen;
import de.sloth.entity.Entity;
import de.sloth.hmi.GameHMI;
import de.sloth.system.game.battle.Battle;
import de.sloth.system.game.battle.BattleEvent;
import de.sloth.system.game.bgmSystem.PlaySong;
import de.sloth.system.game.collision.Collision;
import de.sloth.system.game.collision.CollisionEvent;
import de.sloth.system.game.core.GameCore;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.endConditions.EndCondition;
import de.sloth.system.game.inventory.ChangeCursor;
import de.sloth.system.game.inventory.CollectItem;
import de.sloth.system.game.inventory.InventoryEvent;
import de.sloth.system.game.inventory.UseItem;
import de.sloth.system.game.loot.GenerateEquipment;
import de.sloth.system.game.loot.GeneratePotion;
import de.sloth.system.game.loot.LootEvent;
import de.sloth.system.game.move.Move;
import de.sloth.system.game.move.MoveEvent;
import de.sloth.system.game.startGame.StartGame;
import de.sloth.system.game.startGame.StartGameEvent;
import de.sloth.system.game.systemActivation.ActivateAllSystems;
import de.sloth.system.game.systemActivation.ActivateSystem;
import de.sloth.system.game.systemActivation.GameCoreSystem;
import de.sloth.system.game.systemActivation.SystemActivationEvent;
import de.sloth.system.hmi.HMIGameSystem;
import de.sloth.system.hmi.Render;
import de.sloth.system.hmi.hmiInventory.ChangeInventoryCursor;
import de.sloth.system.hmi.hmiInventory.CloseInventory;
import de.sloth.system.hmi.hmiInventory.HMIInventoryEvent;
import de.sloth.system.hmi.hmiInventory.ShowInventory;
import de.sloth.system.hmi.hmiMenu.HMIMenuEvent;
import de.sloth.system.hmi.hmiMenu.ShowGame;
import de.sloth.system.hmi.hmiMenu.ShowMenu;
import de.sloth.system.hmi.hmiMenu.ShowWin;
import de.sloth.system.hmi.hmiMenu.ToggleGameOver;

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
	
	public GameSystem generateBattleSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem battleSystem = new GameSystem("battleSys", BattleEvent.class, entities, eventQueue);
		battleSystem.registerBehavior("Any", new Battle());
		return battleSystem;
	}
	
	public GameSystem generateEndConditionSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem endConditionSystem = new GameSystem("ecSys", null, entities, eventQueue);
		endConditionSystem.registerBehavior("Any", new EndCondition());
		endConditionSystem.setActive(false);
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
		hmiMenuSystem.registerBehavior("showWin", new ShowWin());
		return hmiMenuSystem;
	}
	
	public GameSystem generateHMIInventorySystem(GameHMI gameHMI, ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		HMIGameSystem hmiInventorySystem = new HMIGameSystem(gameHMI, "hmiInventorySys", HMIInventoryEvent.class, entities, eventQueue);
		hmiInventorySystem.registerBehavior("showInventory", new ShowInventory());
		hmiInventorySystem.registerBehavior("closeInventory", new CloseInventory());
		hmiInventorySystem.registerBehavior("changeCursor", new ChangeInventoryCursor());
		return hmiInventorySystem;
	}
	
	public GameSystem generateSystemActivationSystem(GameCore core, ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameCoreSystem systemActivationSystem = new GameCoreSystem("sysActiveSys", SystemActivationEvent.class, entities, eventQueue, core);
		systemActivationSystem.registerBehavior("single", new ActivateSystem());
		systemActivationSystem.registerBehavior("all", new ActivateAllSystems());
		return systemActivationSystem;
	}
	
	public GameSystem generateBGMSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem bgmSystem = new GameSystem("bgmSys", null, entities, eventQueue);
		bgmSystem.registerBehavior("Any", new PlaySong());
		bgmSystem.setActive(false);
		return bgmSystem;
	}
	
	public GameSystem generateLootSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		GameSystem lootSystem = new GameSystem("lootSys", LootEvent.class, entities, eventQueue);
		lootSystem.registerBehavior("generateEquipment", new GenerateEquipment());
		lootSystem.registerBehavior("generatePotion", new GeneratePotion());
		return lootSystem;
	}
}
