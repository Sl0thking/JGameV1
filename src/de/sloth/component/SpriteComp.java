package de.sloth.component;

import javafx.scene.image.Image;

public class SpriteComp extends Component {
	
	private Image sprite;
	
	public SpriteComp(String imagePath) {
		this.sprite = new Image(imagePath);
	}

	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	
	
}
