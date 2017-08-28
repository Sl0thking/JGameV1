package de.sloth.core.inventory.event;

import de.sloth.core.main.Entity;
import de.sloth.core.main.GameEvent;

/**
 * Event for inventory system
 * @author Kevin Jolitz
 * @date 23.08.2017
 * @version 0.1.0
 */
public class InventoryEvent extends GameEvent {
	private Entity inventoryOwner;
	private Entity targetItem;
	private int invSlot;
	private int amount;
	
	public InventoryEvent(String keyword, Entity inventoryOwner,
			Entity targetItem) {
		super(keyword);
		this.inventoryOwner = inventoryOwner;
		this.targetItem = targetItem;
		this.invSlot = -1;
	}
	
	public InventoryEvent(String keyword, Entity inventoryOwner, int invSlot, int amount) {
		super(keyword);
		this.inventoryOwner = inventoryOwner;
		this.targetItem = null;
		this.invSlot = invSlot;
		this.amount = amount;
	}
	
	public InventoryEvent(String keyword, Entity inventoryOwner, int invSlot) {
		super(keyword);
		this.inventoryOwner = inventoryOwner;
		this.targetItem = null;
		this.invSlot = invSlot;
		this.amount = -1;
	}

	public Entity getInventoryOwner() {
		return inventoryOwner;
	}

	public void setInventoryOwner(Entity inventoryOwner) {
		this.inventoryOwner = inventoryOwner;
	}

	public Entity getTargetItem() {
		return targetItem;
	}

	public void setTargetItem(Entity targetItem) {
		this.targetItem = targetItem;
	}

	public int getInvSlot() {
		return invSlot;
	}

	public void setInvSlot(int invSlot) {
		this.invSlot = invSlot;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}