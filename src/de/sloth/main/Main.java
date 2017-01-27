package de.sloth.main;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.controllHandler.InventoryControllHandler;
import de.sloth.controllHandler.SimpleControllHandler;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.hmi.MainMenuLayer;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.hmi.WinGameLayer;
import de.sloth.hmi.LooseGameLayer;
import de.sloth.hmi.GameHMI;
import de.sloth.hmi.GeneralGameInformation;
import de.sloth.hmi.InventoryGameLayer;
import de.sloth.system.game.BGMusicSystem;
import de.sloth.system.game.BattleSystem;
import de.sloth.system.game.EndConditionSystem;
import de.sloth.system.game.GameCore;
import de.sloth.system.game.InventorySystem;
import de.sloth.system.game.StartGameSystem;
import de.sloth.system.game.movecollision.CollisionTestSystem;
import de.sloth.system.game.movecollision.SimpleEntityMoveSystem;
import de.sloth.system.hmi.HMIManagementSystem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
		int screenHeight = (int) Screen.getPrimary().getBounds().getHeight(); 

		int spriteHeight = 32;
		int spriteWidth = 32;

		GameHMI gameHmi = new GameHMI(screenWidth, screenHeight);
		
		Scene scene = new Scene(gameHmi);
		primaryStage.setScene(scene);
		ConcurrentLinkedQueue<Entity> entities = new ConcurrentLinkedQueue<Entity>();
		ConcurrentLinkedQueue<GameEvent> eventQueue = new ConcurrentLinkedQueue<GameEvent>();
		PlayerInformationLayer pil = new PlayerInformationLayer("playerInfo", eventQueue);
		LooseGameLayer wlgl = new LooseGameLayer("wl", eventQueue);
		WinGameLayer wlg = new WinGameLayer("wlg", eventQueue);
		InventoryGameLayer igl = new InventoryGameLayer("igl", eventQueue);
		GeneralGameInformation ggi = new GeneralGameInformation("ggi", eventQueue);
		MainMenuLayer mml = new MainMenuLayer("main", eventQueue);
		
		gameHmi.registerGameInterfaceLayer(ggi);
		gameHmi.registerGameInterfaceLayer(pil);
		gameHmi.registerGameInterfaceLayer(wlgl);
		gameHmi.registerGameInterfaceLayer(wlg);
		gameHmi.registerGameInterfaceLayer(igl);
		gameHmi.registerGameInterfaceLayer(mml);
		
		SimpleControllHandler stdControll = new SimpleControllHandler(entities, eventQueue, spriteWidth, spriteHeight);
		InventoryControllHandler iControll = new InventoryControllHandler(entities, eventQueue);
		pil.setOnKeyPressed(stdControll);
		mml.requestFocus();
		igl.setOnKeyPressed(iControll);
		GameCore core = new GameCore(entities, eventQueue, gameHmi.getCanvas(), screenWidth, screenHeight);
		((GeneralGameInformation) gameHmi.getGameInterfaceLayer("ggi")).getLabel().textProperty().bindBidirectional(core.getFpsProperty(), new StringConverter<Number>() {
			@Override
			public Number fromString(String arg0) {
				return Integer.parseInt(arg0);
			}
			@Override
			public String toString(Number arg0) {
				return arg0.toString();
			}
		});
				
		SimpleEntityMoveSystem mov = new SimpleEntityMoveSystem(entities, eventQueue, gameHmi.getCanvas().getGraphicContext(0));
		CollisionTestSystem cts = new CollisionTestSystem(entities, eventQueue);
		BattleSystem bsys = new BattleSystem(entities, eventQueue);
		EndConditionSystem ecs = new EndConditionSystem(entities, eventQueue);
		HMIManagementSystem hms = new HMIManagementSystem(entities, eventQueue, gameHmi);
		StartGameSystem rsys = new StartGameSystem(entities, eventQueue);
		InventorySystem isys = new InventorySystem(entities, eventQueue);
		BGMusicSystem bgsys = new BGMusicSystem(entities, eventQueue);
		core.registerSystem(mov);
		core.registerSystem(cts);
		core.registerSystem(bsys);
		core.registerSystem(ecs);
		core.registerSystem(hms);
		core.registerSystem(rsys);
		core.registerSystem(isys);
		core.registerSystem(bgsys);
		core.start();
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}