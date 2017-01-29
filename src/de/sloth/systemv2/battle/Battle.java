package de.sloth.systemv2.battle;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.sloth.component.LivingComp;
import de.sloth.component.LvlComp;
import de.sloth.entity.Entity;
import de.sloth.generators.LootGenerator;
import de.sloth.systemv2.core.GameEvent;
import de.sloth.systemv2.core.GameSystem;
import de.sloth.systemv2.core.IBehavior;

public class Battle implements IBehavior {
	LootGenerator lGen;
	
	private int rollAttack(int min, int max) {
		 Random rand = new Random();
		 return min + rand.nextInt((max-min)+1);
	}

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		List<GameEvent> delEvents = new LinkedList<GameEvent>();
		BattleEvent bEvent = (BattleEvent) expectedEvent;
		Entity attacker = bEvent.getCollisionSrc();
		Entity defender = bEvent.getCollisionTarget();
		LivingComp attackerStatus = (LivingComp) attacker.getComponent(LivingComp.class);
		LivingComp defenderStatus = (LivingComp) defender.getComponent(LivingComp.class);
			
		int attackerValue = rollAttack(attackerStatus.getAttackMin().getValue(), attackerStatus.getAttackMax().getValue());
		int defenderValue = rollAttack(defenderStatus.getAttackMin().getValue(), defenderStatus.getAttackMax().getValue());
			
		defenderStatus.setHp(defenderStatus.getHp() - (attackerValue - defenderStatus.getDefense()));
		attackerStatus.setHp(attackerStatus.getHp() - (defenderValue - attackerStatus.getDefense()));
		if(defenderStatus.getHp() <= 0) {
			system.getEntities().remove(defender);
			LvlComp lcomp = (LvlComp) attacker.getComponent(LvlComp.class);
			int bLvl = lcomp.getLvl().getValue();
				
			lcomp.gainExp(10);
			if(lcomp.getLvl().getValue() > bLvl) {
				attackerStatus.setAttackMin(attackerStatus.getAttackMin().getValue()+2);
				attackerStatus.setAttackMax(attackerStatus.getAttackMax().getValue()+2);
				attackerStatus.setHpMax(attackerStatus.getHpMax()+25);
				attackerStatus.setHp(attackerStatus.getHpMax());
			}
			/*Random rand = new Random();
			int genEquip = rand.nextInt(2);
			InventoryEvent iEvent = new InventoryEvent(InventoryKeyword.collectItem);
			Entity drop;
			if(genEquip == 0) {
				drop = lGen.generateEquipment(1);
			} else {
				drop = new Entity();
				drop.addComponent(new LootableComp());
				drop.addComponent(new HealComp(10));
			}
			iEvent.setCollectingEntity(drop);
			this.getEventQueue().add(iEvent); */
		}
		delEvents.add(expectedEvent);
		system.getEventQueue().removeAll(delEvents);
		
	}
}