package de.sloth.main;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.FocusComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.component.LivingComp;
import de.sloth.controllHandler.SimpleControllHandler;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.hmi.FieldCanvas;
import de.sloth.hmi.MainPane;
import de.sloth.system.BattleSystem;
import de.sloth.system.CollisionTestSystem;
import de.sloth.system.SimpleEntityMoveSystem;
import de.sloth.system.GameCore;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		int spriteHeight = 35;
		int spriteWidth = 40;
		int xFields = 16;
		int yFields = 16;
		
		Canvas canvas = new FieldCanvas(yFields, xFields, spriteHeight, spriteWidth);
		MainPane pane = new MainPane(800, 740, canvas);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		ConcurrentLinkedQueue<Entity> entities = new ConcurrentLinkedQueue<Entity>();
		ConcurrentLinkedQueue<GameEvent> eventQueue = new ConcurrentLinkedQueue<GameEvent>();
		Entity main = new Entity();
		main.setId(1);
		main.addComponent(new Position3DComp());
		((Position3DComp) main.getComponent(Position3DComp.class)).setX(spriteWidth);
		((Position3DComp) main.getComponent(Position3DComp.class)).setY(spriteHeight);
		main.addComponent(new FocusComp(true));
		main.addComponent(new LivingComp(true));
		main.addComponent(new SpriteComp("file:./sprites/hero.png"));
		//main.addComponent(new SimpleGraphicComp("playable"));
		entities.add(main);
		for(int y = 0; y < yFields; y++) {
			for(int x = 0; x < xFields; x++) {
				if(y == 0 || y == yFields-1 || x == 0 || x == xFields-1) {
					Entity wall = new Entity();
					int id = 0;
					for(Entity entity : entities) {
						if(entity.getId() > id) {
							id = entity.getId();
						}
					}
					wall.setId(id+1);
					wall.setName("Wall");
					Position3DComp posComp = new Position3DComp();
					posComp.setX(x*spriteWidth);
					posComp.setY(y*spriteHeight);
					wall.addComponent(posComp);
					wall.addComponent(new SpriteComp("file:./sprites/wall.png"));
					System.out.println(wall);
					entities.add(wall);
				} else if((y != 1 || x != 1) && Math.random() < 0.3) {
					
					Entity enemy = new Entity();
					int id = 0;
					for(Entity entity : entities) {
						if(entity.getId() > id) {
							id = entity.getId();
						}
					}
					enemy.setId(id+1);
					enemy.setName("Enemy");
					Position3DComp posComp = new Position3DComp();
					posComp.setX(x*spriteWidth);
					posComp.setY(y*spriteHeight);
					enemy.addComponent(posComp);
					enemy.addComponent(new SpriteComp("file:./sprites/enemy.png"));
					enemy.addComponent(new LivingComp(true));
					System.out.println(enemy);
					entities.add(enemy);
				}
			}
		}
		SimpleControllHandler stdControll = new SimpleControllHandler(entities, eventQueue, spriteWidth, spriteHeight);
		scene.setOnKeyPressed(stdControll);
		GameCore core = new GameCore(entities, eventQueue, pane.getCanvasContext());
		pane.getLabel().textProperty().bindBidirectional(core.getFpsProperty(), new StringConverter<Number>() {
			@Override
			public Number fromString(String arg0) {
				return Integer.parseInt(arg0);
			}

			@Override
			public String toString(Number arg0) {
				return arg0.toString();
			}
		});
				
		SimpleEntityMoveSystem mov = new SimpleEntityMoveSystem(entities, eventQueue, pane.getCanvasContext());
		CollisionTestSystem cts = new CollisionTestSystem(entities, eventQueue);
		BattleSystem bsys = new BattleSystem(entities, eventQueue);
		
		core.registerSystem(mov);
		core.registerSystem(cts);
		core.registerSystem(bsys);
		core.start();
		//primaryStage.setAlwaysOnTop(true);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}