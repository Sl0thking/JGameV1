package de.sloth.core.controlls;
import java.util.List;

import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;
import de.sloth.core.main.IEntityManagement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllSystem extends GameSystem implements EventHandler<KeyEvent> {

	public ControllSystem(String systemID, IEntityManagement entityManager, List<GameEvent> eventQueue) {
		super(systemID, null, entityManager, eventQueue);
	}
	
	public void registerKey(KeyCode kCode, GameEvent event) {
		this.getKeywordMapping().put(kCode.toString(), new SendEvent(event));
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