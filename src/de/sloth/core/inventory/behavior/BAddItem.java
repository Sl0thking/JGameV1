package de.sloth.core.inventory.behavior;

import de.sloth.core.inventory.component.FullInventoryException;
import de.sloth.core.inventory.component.InventoryComp;
import de.sloth.core.inventory.event.InventoryEvent;
import de.sloth.core.main.Entity;
import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;

public class BAddItem implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) throws FullInventoryException {
		InventoryEvent iEvent = (InventoryEvent) expectedEvent;
		Entity iOwner = iEvent.getInventoryOwner();
		Entity item = iEvent.getTargetItem();
		InventoryComp iComp = (InventoryComp) iOwner.getComponent(InventoryComp.class);
		iComp.addItem(item, 1);
	}
}
