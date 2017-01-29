package de.sloth.systemv2.inventory;

import java.util.LinkedList;
import java.util.List;

import de.sloth.component.FocusComp;
import de.sloth.component.InventoryComponent;
import de.sloth.entity.Entity;
import de.sloth.systemv2.core.GameEvent;
import de.sloth.systemv2.core.GameSystem;
import de.sloth.systemv2.core.IBehavior;

public class CollectItem implements IBehavior {

	public static final int LIMIT = 12;
	
	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		InventoryEvent iEvent = (InventoryEvent) expectedEvent;
		List<Class<?>> filterComps = new LinkedList<Class<?>>();
		filterComps.add(InventoryComponent.class);
		filterComps.add(FocusComp.class);
		Entity mainEntity = system.filterEntitiesByComponents(filterComps).get(0);
		InventoryComponent icomp = (InventoryComponent) mainEntity.getComponent(InventoryComponent.class);
		if(icomp.getInventoryList().size() < LIMIT) {
			icomp.getInventoryList().add(iEvent.getCollectingEntity());
		}
	}
}