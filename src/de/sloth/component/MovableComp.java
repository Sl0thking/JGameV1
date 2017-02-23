package de.sloth.component;

import de.sloth.system.game.moveSystem.Direction;

public class MovableComp extends Component {
	private int speed;
	private Direction direction;
	
	public MovableComp(int speed, Direction direction) {
		this.speed = speed;
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}