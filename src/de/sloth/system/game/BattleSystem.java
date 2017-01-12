package de.sloth.system.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.LivingComp;
import de.sloth.component.LootComp;
import de.sloth.component.HealComp;
import de.sloth.component.InventoryComponent;
import de.sloth.component.LvlComp;
import de.sloth.entity.Entity;
import de.sloth.event.BattleEvent;
import de.sloth.event.GameEvent;
import de.sloth.system.GameSystem;

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
			
			int attackerValue = rollAttack(attackerStatus.getAttackMin().getValue(), attackerStatus.getAttackMax().getValue());
			int defenderValue = rollAttack(defenderStatus.getAttackMin().getValue(), defenderStatus.getAttackMax().getValue());
			
			defenderStatus.setHp(defenderStatus.getHp() - (attackerValue - defenderStatus.getDefense().getValue()));
			attackerStatus.setHp(attackerStatus.getHp() - (defenderValue - attackerStatus.getDefense().getValue()));
			if(defenderStatus.getHp() <= 0) {
				this.getEntities().remove(defender);
				LvlComp lcomp = (LvlComp) attacker.getComponent(LvlComp.class);
				int bLvl = lcomp.getLvl().getValue();
				
				lcomp.gainExp(10);
				if(lcomp.getLvl().getValue() > bLvl) {
					attackerStatus.setAttackMin(attackerStatus.getAttackMin().getValue()+2);
					attackerStatus.setAttackMax(attackerStatus.getAttackMax().getValue()+2);
					attackerStatus.setHpMax(attackerStatus.getHpMax()+25);
					attackerStatus.setHp(attackerStatus.getHpMax());
				}
				Entity potion = new Entity();
				potion.addComponent(new LootComp());
				potion.addComponent(new HealComp(10));
				((InventoryComponent) attacker.getComponent(InventoryComponent.class)).getInventoryList().add(potion);
			}
			delEvents.add(event);
		}
		this.getEventQueue().removeAll(delEvents);
	}
	
	private int rollAttack(int min, int max) {
		 Random rand = new Random();
		 return min + rand.nextInt((max-min)+1);
	}
}