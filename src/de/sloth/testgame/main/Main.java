package de.sloth.testgame.main;

import java.util.concurrent.ConcurrentLinkedQueue;


import de.sloth.system.game.core.GameCore;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.IEntityManagement;
import de.sloth.hmi.HMICore;
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

		IEntityManagement entityManager = new EntityManager();
		HMICore gameHmi = new HMICore(screenWidth, screenHeight);
		
		Scene scene = new Scene(gameHmi);
		primaryStage.setScene(scene);
		ConcurrentLinkedQueue<GameEvent> eventQueue = new ConcurrentLinkedQueue<GameEvent>();
		//Register hmi layers
		gameHmi.getCanvas().setOnKeyPressed(GameSystemGenerator.getInstance().generateGameControllSystem(entityManager, eventQueue));
		gameHmi.getCanvas().requestFocus();
		//initate controlls
		
		GameCore core = new GameCore();
		
		core.registerSystem(GameSystemGenerator.getInstance().generateSystemActivationSystem(entityManager, core, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateMoveSystem(entityManager, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateBGMSystem(entityManager, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateStartGameSystem(entityManager, eventQueue));
		core.registerSystem(GameSystemGenerator.getInstance().generateRenderSystem(entityManager, gameHmi, eventQueue));
		core.start();
		eventQueue.add(new StartGameEvent());
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