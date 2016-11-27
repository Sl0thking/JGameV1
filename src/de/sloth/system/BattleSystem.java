package de.sloth.system;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.LivingComp;
import de.sloth.entity.Entity;
import de.sloth.event.BattleEvent;
import de.sloth.event.GameEvent;

public class BattleSystem extends GameSystem {

	public BattleSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(entities, eventQueue);
		// TODO Auto-generated constructor stub
	}

	public BattleSystem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeSystem() {
		List<GameEvent> delEvents = new LinkedList<GameEvent>();
		for(GameEvent event:this.getEventQueue()) {
			if(event.getClass().equals(BattleEvent.class)) {
				BattleEvent bEvent = (BattleEvent) event;
				Entity attacker = bEvent.getCollisionSrc();
				Entity defender = bEvent.getCollisionTarget();
				LivingComp attackerStatus = (LivingComp) attacker.getComponent(LivingComp.class);
				LivingComp defenderStatus = (LivingComp) defender.getComponent(LivingComp.class);
				defenderStatus.setHp(defenderStatus.getHp() - (attackerStatus.getAttack() - defenderStatus.getDefense()));
				System.out.println(defender);
				if(defenderStatus.getHp() <= 0) {
					this.getEntities().remove(defender);
				}
				delEvents.add(event);
			}
		}
		this.getEventQueue().removeAll(delEvents);
	}
}