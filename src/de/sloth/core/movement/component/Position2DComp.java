package de.sloth.core.movement.component;

import de.sloth.core.main.component.Component;

/**
 * Position data for a 2 dimensional room (without z)
 * [SlothCore]
 * @author Slothking
 *
 */
public class Position2DComp extends Component {
	private int x = 0;
	private int y = 0;
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}	
}