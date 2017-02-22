package de.sloth.component;

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