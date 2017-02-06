package de.sloth.testgame.main;

import de.sloth.component.FocusComp;
import de.sloth.component.Position3DComp;
import de.sloth.component.SpriteComp;
import de.sloth.entity.Entity;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class StartGame implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		Entity sloth = new Entity();
		sloth.setName("Viking");
		Position3DComp posComp = new Position3DComp();
		posComp.setY(500);
		FocusComp fComp = new FocusComp();
		SpriteComp sComp = new SpriteComp("Viking_right.png");
		sloth.addComponent(posComp);
		sloth.addComponent(fComp);
		sloth.addComponent(sComp);
		system.getEntityManager().addEntity(sloth);
		Entity sloth2 = new Entity();
		sloth2.setName("Viking");
		Position3DComp pos2Comp = new Position3DComp();
		pos2Comp.setX(64);
		pos2Comp.setY(64);
		SpriteComp s2Comp = new SpriteComp("Viking_left.png");
		sloth2.addComponent(pos2Comp);
		sloth2.addComponent(s2Comp);
		system.getEntityManager().addEntity(sloth2);
		System.out.println("EXECUTED SLOTH GEN");
	}
}