package de.sloth.main;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.FocusComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.component.LivingComp;
import de.sloth.component.LvlComp;
import de.sloth.controllHandler.InventoryControllHandler;
import de.sloth.controllHandler.SimpleControllHandler;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.hmi.PlayerInformationLayer;
import de.sloth.hmi.RestartEvent;
import de.sloth.hmi.WinLooseGameLayer;
import de.sloth.hmi.GameHMI;
import de.sloth.hmi.GeneralGameInformation;
import de.sloth.hmi.InventoryGameLayer;
import de.sloth.system.BattleSystem;
import de.sloth.system.CollisionTestSystem;
import de.sloth.system.EndConditionSystem;
import de.sloth.system.SimpleEntityMoveSystem;
import de.sloth.system.StartEvent;
import de.sloth.system.GameCore;
import de.sloth.system.HMIManagementSystem;
import de.sloth.system.StartGameSystem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
		int screenHeight = (int) Screen.getPrimary().getBounds().getHeight(); 

		int spriteHeight = 35;
		int spriteWidth = 40;

		GameHMI gameHmi = new GameHMI(screenWidth, screenHeight);
		
		Scene scene = new Scene(gameHmi);
		primaryStage.setScene(scene);
		ConcurrentLinkedQueue<Entity> entities = new ConcurrentLinkedQueue<Entity>();
		ConcurrentLinkedQueue<GameEvent> eventQueue = new ConcurrentLinkedQueue<GameEvent>();
		PlayerInformationLayer pil = new PlayerInformationLayer("playerInfo", eventQueue);
		WinLooseGameLayer wlgl = new WinLooseGameLayer("wl", eventQueue);
		InventoryGameLayer igl = new InventoryGameLayer("igl", eventQueue);
		GeneralGameInformation ggi = new GeneralGameInformation("ggi", eventQueue);
		gameHmi.registerGameInterfaceLayer(ggi);
		gameHmi.registerGameInterfaceLayer(pil);
		gameHmi.registerGameInterfaceLayer(wlgl);
		gameHmi.registerGameInterfaceLayer(igl);
		
		SimpleControllHandler stdControll = new SimpleControllHandler(entities, eventQueue, spriteWidth, spriteHeight);
		InventoryControllHandler iControll = new InventoryControllHandler(entities, eventQueue);
		pil.setOnKeyPressed(stdControll);
		pil.requestFocus();
		igl.setOnKeyPressed(iControll);
		GameCore core = new GameCore(entities, eventQueue, gameHmi.getCanvasContext());
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
				
		SimpleEntityMoveSystem mov = new SimpleEntityMoveSystem(entities, eventQueue, gameHmi.getCanvasContext());
		CollisionTestSystem cts = new CollisionTestSystem(entities, eventQueue);
		BattleSystem bsys = new BattleSystem(entities, eventQueue);
		EndConditionSystem ecs = new EndConditionSystem(entities, eventQueue);
		HMIManagementSystem hms = new HMIManagementSystem(entities, eventQueue, gameHmi);
		StartGameSystem rsys = new StartGameSystem(entities, eventQueue);
		core.registerSystem(mov);
		core.registerSystem(cts);
		core.registerSystem(bsys);
		core.registerSystem(ecs);
		core.registerSystem(hms);
		core.registerSystem(rsys);
		core.start();
		primaryStage.setFullScreen(true);
		eventQueue.add(new StartEvent());
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}