package de.sloth.system.game;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.EnemyComp;
import de.sloth.component.FocusComp;
import de.sloth.component.InventoryComponent;
import de.sloth.component.LivingComp;
import de.sloth.component.LvlComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.event.HMIEvent;
import de.sloth.event.HMIKeyword;
import de.sloth.event.RestartEvent;
import de.sloth.event.StartEvent;
import de.sloth.system.GameSystem;
import de.sloth.system.SystemActivationEvent;
import javafx.stage.Screen;

public class StartGameSystem extends GameSystem {
	
	public StartGameSystem() {
		super();
	}

	public StartGameSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(entities, eventQueue);
	}

	@Override
	public void executeSystem() {
		List<GameEvent> delEvents = new LinkedList<GameEvent>();
		for(GameEvent event:this.getEventQueue()) {
			if(event.getClass().equals(RestartEvent.class) || event.getClass().equals(StartEvent.class)) {
				int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
				int screenHeight = (int) Screen.getPrimary().getBounds().getHeight(); 

				int spriteHeight = 35;
				int spriteWidth = 40;
				int xFields = screenWidth / spriteWidth;
				int yFields = screenHeight / spriteHeight;
				this.getEntities().clear();
				Entity main = new Entity();
				main.setId(1);
				main.addComponent(new Position3DComp());
				((Position3DComp) main.getComponent(Position3DComp.class)).setX(spriteWidth);
				((Position3DComp) main.getComponent(Position3DComp.class)).setY(spriteHeight);
				main.addComponent(new FocusComp(true));
				LivingComp lComp = new LivingComp(true);
				lComp.setHp(50);
				lComp.setHpMax(50);
				LvlComp lvlcomp = new LvlComp();
				main.addComponent(lComp);
				main.addComponent(lvlcomp);
				main.addComponent(new InventoryComponent());
				main.addComponent(new SpriteComp("file:./sprites/hero.png"));
				//main.addComponent(new SimpleGraphicComp("playable"));
				this.getEntities().add(main);
				for(int y = 0; y < yFields; y++) {
					for(int x = 0; x < xFields; x++) {
						Entity floor = new Entity();
						int id = 0;
						for(Entity entity : this.getEntities()) {
							if(entity.getId() > id) {
								id = entity.getId();
							}
						}
						floor.setId(id+1);
						floor.setName("Floor");
						Position3DComp posComp = new Position3DComp();
						posComp.setX(x*spriteWidth);
						posComp.setY(y*spriteHeight);
						posComp.setZ(0);
						floor.addComponent(posComp);
						floor.addComponent(new SpriteComp("file:./sprites/floor.png"));
						this.getEntities().add(floor);
						if(y == 0 || y == yFields-1 || x == 0 || x == xFields-1) {
							Entity wall = new Entity();
							id = 0;
							for(Entity entity : this.getEntities()) {
								if(entity.getId() > id) {
									id = entity.getId();
								}
							}
							wall.setId(id+1);
							wall.setName("Wall");
							posComp = new Position3DComp();
							posComp.setX(x*spriteWidth);
							posComp.setY(y*spriteHeight);
							wall.addComponent(posComp);
							wall.addComponent(new SpriteComp("file:./sprites/wall.png"));
							System.out.println(wall);
							this.getEntities().add(wall);
						} else if((y != 1 || x != 1) && Math.random() < 0.02) {
							Entity enemy = new Entity();
							id = 0;
							for(Entity entity : this.getEntities()) {
								if(entity.getId() > id) {
									id = entity.getId();
								}
							}
							enemy.setId(id+1);
							enemy.setName("Enemy");
							posComp = new Position3DComp();
							posComp.setX(x*spriteWidth);
							posComp.setY(y*spriteHeight);
							enemy.addComponent(posComp);
							enemy.addComponent(new SpriteComp("file:./sprites/enemy.png"));
							enemy.addComponent(new LivingComp(true));
							enemy.addComponent(new EnemyComp());
							System.out.println(enemy);
							this.getEntities().add(enemy);
						}
					}
				}
				delEvents.add(event);
				this.getEventQueue().add(new SystemActivationEvent(EndConditionSystem.class));
				HMIEvent hmiEvent;
				if(event.getClass().equals(RestartEvent.class)) {
					hmiEvent = new HMIEvent(HMIKeyword.toggleGameOver);
				} else {
					hmiEvent = new HMIEvent(HMIKeyword.startGame);
				}
				this.getEventQueue().add(hmiEvent);
			}
		}
		this.getEventQueue().removeAll(delEvents);
		
	}

}
