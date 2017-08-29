package de.sloth.rpg.inventory.behavior;

import de.sloth.rpg.inventory.component.InventoryComp;
import de.sloth.rpg.inventory.event.InventoryEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;

public class BRemoveItem implements IBehavior{

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		InventoryEvent iEvent = (InventoryEvent) expectedEvent;
		Entity iOwner = iEvent.getInventoryOwner();
		int slot = iEvent.getInvSlot();
		int amount = iEvent.getAmount();
		InventoryComp iComp = (InventoryComp) iOwner.getComponent(InventoryComp.class);
		if(amount > 1) {
			iComp.dropItems(slot, amount);
		} else if(amount == 1){
			iComp.dropItem(slot);
		} else {
			iComp.dropItems(slot);
		}
	}
}