package de.sloth.main;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.FocusComp;
import de.sloth.component.Position2DComp;
import de.sloth.controllHandler.SimpleControllHandler;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.hmi.MainPane;
import de.sloth.system.CollisionTestSystem;
import de.sloth.system.SimpleEntityMoveSystem;
import de.sloth.system.SystemCore;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		MainPane pane = new MainPane(800, 740, 800, 640);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		ConcurrentLinkedQueue<Entity> entities = new ConcurrentLinkedQueue<Entity>();
		ConcurrentLinkedQueue<GameEvent> eventQueue = new ConcurrentLinkedQueue<GameEvent>();
		Entity main = new Entity();
		main.setId(1);
		main.addComponent(new Position2DComp());
		main.addComponent(new FocusComp(true));
		entities.add(main);
		Entity second = new Entity();
		second.setId(2);
		second.addComponent(new Position2DComp());
		((Position2DComp) second.getComponent(Position2DComp.class)).setX(64);
		((Position2DComp) second.getComponent(Position2DComp.class)).setY(128);
		entities.add(second);
		SimpleControllHandler stdControll = new SimpleControllHandler(entities, eventQueue, 32);
		scene.setOnKeyPressed(stdControll);
		SystemCore core = new SystemCore(entities, eventQueue, pane.getCanvasContext());
		SimpleEntityMoveSystem mov = new SimpleEntityMoveSystem(entities, eventQueue, pane.getCanvasContext());
		CollisionTestSystem cts = new CollisionTestSystem(entities, eventQueue);
		mov.start();
		cts.start();
		core.start();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}