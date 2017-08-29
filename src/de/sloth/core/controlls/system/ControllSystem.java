package de.sloth.core.controlls.system;
import java.util.List;

import de.sloth.core.controlls.behavior.BSendEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.GameSystem;
import de.sloth.core.main.system.IEntityManagement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllSystem extends GameSystem implements EventHandler<KeyEvent> {

	public ControllSystem(String systemID, IEntityManagement entityManager, List<GameEvent> eventQueue) {
		super(systemID, null, entityManager, eventQueue);
	}
	
	public void registerKey(KeyCode kCode, GameEvent event) {
		this.getKeywordMapping().put(kCode.toString(), new BSendEvent(event));
	}
	
	@Override
	public void executeSystem() {}
	
	public void executeSystem(KeyCode kCode) {
		IBehavior action = this.getKeywordMapping().get(kCode.toString());
		if(action != null) {
			action.execute(this);
		}
	}
	
	@Override
	public void handle(KeyEvent event) {
		this.executeSystem(event.getCode());
	}
}