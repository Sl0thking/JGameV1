package de.sloth.xdeprecated;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.EnemyComp;
import de.sloth.component.FocusComp;
import de.sloth.component.LivingComp;
import de.sloth.entity.Entity;
import de.sloth.system.GameSystem;
import de.sloth.xdeprecated.GameEvent;
import de.sloth.xdeprecated.HMIEvent;
import de.sloth.xdeprecated.HMIKeyword;

public class EndConditionSystem extends GameSystem {

	public EndConditionSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(entities, eventQueue);
		this.setActive(false);
	}

	public EndConditionSystem() {
		this.setActive(false);
	}

	@Override
	public void executeSystem() {
		super.executeSystem();
		if(this.isActive()) {
			checkLoose();
			checkWin();
		}
	}
	
	private void checkWin() {
		List<Class<?>> enemyComps = new LinkedList<Class<?>>();
		enemyComps.add(EnemyComp.class);
		List<Entity> enemies = this.filterEntitiesByComponents(enemyComps);
		if(enemies.size() == 0) {
			HMIEvent event = new HMIEvent(HMIKeyword.showWin);
			this.getEventQueue().add(event);
			this.setActive(false);
		}
	}

	private void checkLoose() {
		List<Class<?>> playerComps = new LinkedList<Class<?>>();
		playerComps.add(FocusComp.class);
		playerComps.add(LivingComp.class);
		List<Entity> players = this.filterEntitiesByComponents(playerComps);
		if(players.size() > 0) {
			Entity player = players.get(0);
			LivingComp lComp = (LivingComp) player.getComponent(LivingComp.class);
			if(lComp.getHp() <= 0) {
				this.getEntities().remove(player);
				HMIEvent event = new HMIEvent(HMIKeyword.toggleGameOver);
				this.getEventQueue().add(event);
				this.setActive(false);
			} 
		}
	}
}