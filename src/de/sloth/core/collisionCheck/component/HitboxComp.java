package de.sloth.core.collisionCheck.component;

import de.sloth.core.main.component.Component;

/**
 * Hitbox dimension for internal collision checking
 * [SlothCore]
 * @author Slothking
 */
public class HitboxComp extends Component {
	private int width;
	private int height;
	
	public HitboxComp(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}