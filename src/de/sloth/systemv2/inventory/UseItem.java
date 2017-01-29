package de.sloth.systemv2.inventory;

import java.util.LinkedList;
import java.util.List;

import de.sloth.component.EquipableComp;
import de.sloth.component.FocusComp;
import de.sloth.component.HealComp;
import de.sloth.component.InventoryComponent;
import de.sloth.component.LivingComp;
import de.sloth.entity.Entity;
import de.sloth.systemv2.core.GameEvent;
import de.sloth.systemv2.core.GameSystem;
import de.sloth.systemv2.core.IBehavior;

public class UseItem implements IBehavior {

	@Override
	public void execute(GameSystem system) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		List<Class<?>> filterComps = new LinkedList<Class<?>>();
		filterComps.add(InventoryComponent.class);
		filterComps.add(FocusComp.class);
		Entity mainEntity = system.filterEntitiesByComponents(filterComps).get(0);
		InventoryComponent icomp = (InventoryComponent) mainEntity.getComponent(InventoryComponent.class);
		//to be excluded
		if(icomp.getCurrentSlotX() < icomp.getInventoryList().size()) {
			Entity usedItem = icomp.getInventoryList().get(icomp.getCurrentSlotX());
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
			icomp.getInventoryList().remove(icomp.getCurrentSlotX());
		}

	}

}
