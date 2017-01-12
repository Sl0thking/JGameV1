package de.sloth.event;

public class HMIInventoryEvent extends HMIEvent {
	int posX;
	int posY;
	
	public HMIInventoryEvent(int posX, int posY) {
		super(HMIKeyword.inventoryEvent);
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}