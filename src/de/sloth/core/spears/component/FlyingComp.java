package de.sloth.core.spears.component;

import de.sloth.core.main.component.Component;
import de.sloth.core.movement.event.Direction;

public class FlyingComp extends Component {
	private boolean isFlying;
	private Direction direct;
	private int tickDuration;
	
	public FlyingComp(int tickDuration, Direction direction) {
		super();
		this.isFlying = true;
		this.tickDuration = tickDuration;
		this.setDirect(direction);
	}

	public boolean isFlying() {
		return isFlying;
	}

	public void setFlying(boolean isFlying) {
		this.isFlying = isFlying;
	}

	public int getTickDuration() {
		return tickDuration;
	}

	public void setTickDuration(int tickDuration) {
		this.tickDuration = tickDuration;
	}

	public Direction getDirect() {
		return direct;
	}

	public void setDirect(Direction direct) {
		this.direct = direct;
	}
}