package de.sloth.system;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.LivingComp;
import de.sloth.component.LvlComp;
import de.sloth.entity.Entity;
import de.sloth.event.BattleEvent;
import de.sloth.event.GameEvent;

public class BattleSystem extends GameSystem {

	public BattleSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(entities, eventQueue);
	}

	public BattleSystem() {}

	@Override
	public void executeSystem() {
		List<GameEvent> delEvents = new LinkedList<GameEvent>();
		GameEvent event = this.checkEvents(BattleEvent.class);
		if(event != null) {
			BattleEvent bEvent = (BattleEvent) event;
			Entity attacker = bEvent.getCollisionSrc();
			Entity defender = bEvent.getCollisionTarget();
			LivingComp attackerStatus = (LivingComp) attacker.getComponent(LivingComp.class);
			LivingComp defenderStatus = (LivingComp) defender.getComponent(LivingComp.class);
			defenderStatus.setHp(defenderStatus.getHp() - (attackerStatus.getAttack() - defenderStatus.getDefense()));
			attackerStatus.setHp(attackerStatus.getHp() - (defenderStatus.getAttack() - defenderStatus.getDefense()));
			if(defenderStatus.getHp() <= 0) {
				this.getEntities().remove(defender);
				((LvlComp) attacker.getComponent(LvlComp.class)).gainExp(10);
			}
			delEvents.add(event);
		}
		this.getEventQueue().removeAll(delEvents);
	}
}