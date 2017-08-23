package de.sloth.hmi;

import de.sloth.hmi.system.SpriteLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public class LayeredCanvas extends StackPane {
	int layers;
	int canvasWidth;
	int canvasHeight;
	
	public LayeredCanvas(int layers, int canvasWidth, int canvasHeight, SpriteLoader loader) {
		this.layers = layers;
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		for(int i = 0; i < layers; i++) {
			this.getChildren().add(new TripleBufferCanvas(canvasWidth, canvasHeight, loader));
		}
	}
	
	public void clear() {
		((Canvas) this.getChildren().get(0)).getGraphicsContext2D().fillRect(0, 0, canvasWidth, canvasHeight);
		for(int i = 1; i < layers; i++) {
			((Canvas) this.getChildren().get(i)).getGraphicsContext2D().clearRect(0, 0, canvasWidth, canvasHeight);
		}
	}
	
	public GraphicsContext getGraphicContext(int layer) {
		return ((Canvas) this.getChildren().get(layer)).getGraphicsContext2D();
	}

	public int getLayers() {
		return this.layers;
	}

	public TripleBufferCanvas getLayer(int layer) {
		return (TripleBufferCanvas) this.getChildren().get(layer);
	}
}