package de.sloth.component;

public class MovableComp extends Component {
	private int speed;
	
	public MovableComp(int speed) {
		this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}