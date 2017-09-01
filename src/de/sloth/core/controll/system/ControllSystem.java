package de.sloth.core.controll.system;
import java.util.List;

import de.sloth.core.controll.behavior.BSendEvent;
import de.sloth.core.main.behavior.IBehavior;
import de.sloth.core.main.event.GameEvent;
import de.sloth.core.main.system.DefaultGameSystem;
import de.sloth.core.main.system.IEntityManagement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControllSystem extends DefaultGameSystem implements EventHandler<KeyEvent> {

	private List<KeyCode> keyCodeBuffer;
	
	public ControllSystem(String systemID, IEntityManagement entityManager, List<GameEvent> eventQueue) {
		super(systemID, null, entityManager, eventQueue);
	}
	
	public void registerKeyToBehavior(KeyCode kCode, IBehavior behavior) {
		this.getKeywordMapping().put(kCode.toString(), behavior);
	}
	
	@Override
	public void executeSystem() {
		if(keyCodeBuffer.size() > 0) {
			KeyCode keyCode = keyCodeBuffer.get(0);
			keyCodeBuffer.remove(0);
			this.executeSystem(keyCode);
		}
	}
	
	public void executeSystem(KeyCode kCode) {
		IBehavior action = this.getKeywordMapping().get(kCode.toString());
		if(action != null) {
			action.execute(this);
		}
	}
	
	@Override
	public void handle(KeyEvent event) {
		this.keyCodeBuffer.add(event.getCode());
	}
}