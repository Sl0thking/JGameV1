package de.sloth.core.inventory.behavior;

import de.sloth.core.inventory.component.InventoryComp;
import de.sloth.core.inventory.event.InventoryEvent;
import de.sloth.core.main.Entity;
import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;

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