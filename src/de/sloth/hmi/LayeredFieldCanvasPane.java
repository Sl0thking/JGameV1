package de.sloth.hmi;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public class LayeredFieldCanvasPane extends StackPane {
	int layers;
	
	public LayeredFieldCanvasPane(int layers, int screenWidth, int screenHeight) {
		this.layers = layers;
		for(int i = 0; i < layers; i++) {
			this.getChildren().add(new Canvas(screenWidth, screenHeight));
		}
	}
	
	public GraphicsContext getGraphicContext(int layer) {
		return ((Canvas) this.getChildren().get(layer)).getGraphicsContext2D();
	}

	public int getLayers() {
		return this.layers;
	}
}