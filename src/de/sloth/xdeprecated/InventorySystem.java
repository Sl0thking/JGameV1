package de.sloth.xdeprecated;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.component.EquipableComp;
import de.sloth.component.FocusComp;
import de.sloth.component.HealComp;
import de.sloth.component.InventoryComponent;
import de.sloth.component.LivingComp;
import de.sloth.entity.Entity;
import de.sloth.system.GameSystem;
import de.sloth.xdeprecated.GameEvent;
import de.sloth.xdeprecated.HMIInventoryEvent;
import de.sloth.xdeprecated.InventoryEvent;
import de.sloth.xdeprecated.InventoryKeyword;

public class InventorySystem extends GameSystem {

	private int posX;
	private int posY;
	private final int LIMIT = 12;
	
	public InventorySystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(entities, eventQueue);
		posX = 0;
		posY = 0;
	}
	
	@Override
	public void executeSystem() {
		InventoryEvent iEvent = (InventoryEvent) this.checkEvents(InventoryEvent.class);
		if(iEvent != null) {
			if(iEvent.getKeyword().equals(InventoryKeyword.changeCursor)) {
				this.posX += iEvent.getxPos();
				if(this.posX > 14) {
					this.posX = 0;
				} else if(this.posX < 0) {
					this.posX = 14;
				}
				this.getEventQueue().add(new HMIInventoryEvent(this.posX, this.posY));
				
			} else if(iEvent.getKeyword().equals(InventoryKeyword.collectItem)) {
				List<Class<?>> filterComps = new LinkedList<Class<?>>();
				filterComps.add(InventoryComponent.class);
				filterComps.add(FocusComp.class);
				Entity mainEntity = this.filterEntitiesByComponents(filterComps).get(0);
				InventoryComponent icomp = (InventoryComponent) mainEntity.getComponent(InventoryComponent.class);
				if(icomp.getInventoryList().size() < LIMIT) {
					icomp.getInventoryList().add(iEvent.getCollectingEntity());
				}
			} else if(iEvent.getKeyword().equals(InventoryKeyword.useItem)) {
				List<Class<?>> filterComps = new LinkedList<Class<?>>();
				filterComps.add(InventoryComponent.class);
				filterComps.add(FocusComp.class);
				Entity mainEntity = this.filterEntitiesByComponents(filterComps).get(0);
				InventoryComponent icomp = (InventoryComponent) mainEntity.getComponent(InventoryComponent.class);
				//to be excluded
				if(posX < icomp.getInventoryList().size()) {
					Entity usedItem = icomp.getInventoryList().get(posX);
					if(usedItem.getComponent(HealComp.class) != null) {
						HealComp hc = (HealComp) usedItem.getComponent(HealComp.class);
						LivingComp lc = (LivingComp) mainEntity.getComponent(LivingComp.class);
						lc.setHp(lc.getHp() + hc.getHealValue());
					} else if(usedItem.getComponent(EquipableComp.class) != null) {
						EquipableComp ecomp = (EquipableComp) usedItem.getComponent(EquipableComp.class);
						LivingComp lc = (LivingComp) mainEntity.getComponent(LivingComp.class);
						lc.setAttackMax(lc.getAttackMax().getValue() + ecomp.getDmgBonus());
						lc.setAttackMin(lc.getAttackMin().getValue() + ecomp.getDmgBonus());
						lc.setDefense(lc.getDefense() + ecomp.getArmorBonus());
					}
					icomp.getInventoryList().remove(posX);
				}
			}
			this.getEventQueue().remove(iEvent);
		}
	}

}
