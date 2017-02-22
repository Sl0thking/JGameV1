package de.sloth.system.game.controlls;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import de.sloth.system.game.core.IEntityManagement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllSystem extends GameSystem implements EventHandler<KeyEvent> {

	public ControllSystem(String systemID, IEntityManagement entityManager, ConcurrentLinkedQueue<GameEvent> eventQueue) {
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