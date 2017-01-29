package de.sloth.xdeprecated;

import de.sloth.entity.Entity;

public class InventoryEvent extends GameEvent {
	InventoryKeyword keyword;
	int xPos;
	int yPos;
	Entity collectedEntity;
	
	public InventoryEvent(InventoryKeyword keyword) {
		this.keyword = keyword;
	}

	public InventoryKeyword getKeyword() {
		return keyword;
	}

	public void setKeyword(InventoryKeyword keyword) {
		this.keyword = keyword;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public Entity getCollectingEntity() {
		return collectedEntity;
	}
	
	public void setCollectingEntity(Entity entity) {
		this.collectedEntity = entity;
	}
}