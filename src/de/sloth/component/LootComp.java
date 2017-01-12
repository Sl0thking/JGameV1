package de.sloth.component;

import javafx.scene.image.Image;

public class LootComp extends Component {
	
	Image image;
	String name;
	
	public LootComp(Image image, String name) {
		super();
		this.image = image;
		this.name = name;
	}

	public LootComp() {
		super();
		this.image = new Image("file:./sprites/potion.png");
		this.name = "Potion";
	}
	
	public Image getImage() {
		return this.image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
