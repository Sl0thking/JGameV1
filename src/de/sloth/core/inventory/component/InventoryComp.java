package de.sloth.core.inventory.component;


import java.util.Arrays;

import de.sloth.core.inventory.datatype.InventorySlot;
import de.sloth.core.main.Entity;
import de.sloth.core.main.component.Component;

public class InventoryComp extends Component {

	private InventorySlot[] inventory;
	private int maxAmount;

	public InventoryComp(int invSize, int maxAmount) {
		super();
		inventory = new InventorySlot[invSize];
		this.maxAmount = maxAmount;
	}
	
	public InventoryComp() {
		super();
		inventory = new InventorySlot[1];
		maxAmount = 1;
	}
	
	public void addItem(Entity entity, int amount) throws FullInventoryException {
		int index = -1;
		boolean alreadyInInventory = false;
		//check for already given item and increase amount
		for(int i = 0; i < inventory.length && !alreadyInInventory; i++) {
			//save first index of empty inv slot
			if(inventory[i] == null && index == -1) {
				index = i;
			} else if(inventory[i] != null) {
				UsableItemComp uiCompInv = (UsableItemComp) inventory[i].getEntity().getComponent(UsableItemComp.class);
				UsableItemComp uiCompEntity = (UsableItemComp) entity.getComponent(UsableItemComp.class);
				if(uiCompInv.equals(uiCompEntity) && inventory[i].getAmount() < this.maxAmount) {
					inventory[i].setAmount(inventory[i].getAmount() + amount);
					alreadyInInventory = true;
				}
			}
		}
		if(!alreadyInInventory && index > -1) {
			inventory[index] = new InventorySlot(amount, entity);
		} else if(!alreadyInInventory && index == -1) {
			throw new FullInventoryException();
		}
	}
	
	public Entity getItem(int slot) {
		if(inventory[slot] != null) {
			return inventory[slot].getEntity();
		} else {
			return null;
		}
	}
	
	public int getItemAmount(int slot) {
		return inventory[slot].getAmount();
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	public InventorySlot[] getInventory() {
		return inventory;
	}
	
	public void clearInventory() {
		this.inventory = new InventorySlot[inventory.length];
	}

	@Override
	public String toString() {
		return "InventoryComp [inventory=" + Arrays.toString(inventory)
				+ ", maxAmount=" + maxAmount + "]";
	}

	public void dropItems(int slot) {
		this.inventory[slot] = null;
	}
	
	public void dropItems(int slot, int amount) {
		if(amount >= this.maxAmount || this.inventory[slot].getAmount() <= amount) {
			this.dropItems(slot);
		} else {
			this.inventory[slot].setAmount(this.inventory[slot].getAmount() - amount);
		}
	}
	
	public void dropItem(int slot) {
		System.out.println("Called");
		if((this.inventory[slot].getAmount() - 1) == 0) {
			this.dropItems(slot);
		} else {
			this.inventory[slot].setAmount(this.inventory[slot].getAmount() - 1);
		}
	}
}
