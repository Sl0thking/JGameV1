package de.sloth.rpg.inventory.behavior;

import de.sloth.rpg.inventory.component.FullInventoryException;
import de.sloth.rpg.inventory.component.InventoryComp;
import de.sloth.rpg.inventory.event.InventoryEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.entity.Entity;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;

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
