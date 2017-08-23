package de.sloth.hmi;

import de.sloth.hmi.system.SpriteLoader;
import javafx.scene.canvas.Canvas;

public class TripleBufferCanvas extends Canvas {
	SpriteLoader loader;
	
	public TripleBufferCanvas(SpriteLoader loader) {
		super();
		this.loader = loader;
	}

	public TripleBufferCanvas(double width, double height, SpriteLoader loader) {
		super(width, height);
		this.loader = loader;
	}
	
	public void drawSprite(String spriteName, int xPos, int yPos) {
		this.getGraphicsContext2D().drawImage(loader.getSprite(spriteName), xPos, yPos);
	}
}