package de.sloth.system;

import de.sloth.component.Position2DComp;
import de.sloth.entity.Entity;

public class SystemCore extends GameSystem {

	@Override
	public void run() {
		Entity entity = new Entity();
		entity.setId(1);
		entity.addComponent(new Position2DComp());
		this.getEntities().add(entity);
		DrawSystem drawSystem = new DrawSystem(20, 20);
		drawSystem.setEntities(this.getEntities());
		drawSystem.start();
		ControllSystem controll = new ControllSystem();
		controll.setEntities(this.getEntities());
		controll.start();
		while(true) {}
	}
}