package de.sloth.rpg.inventory.datatype;

import de.sloth.core.main.entity.Entity;

public class InventorySlot {
	private int amount;
	private Entity entity;
	
	public InventorySlot(int amount, Entity entity) {
		super();
		this.amount = amount;
		this.entity = entity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "InventorySlot [amount=" + amount + ", entity=" + entity + "]";
	}
}
