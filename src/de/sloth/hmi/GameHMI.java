package de.sloth.hmi;

import java.util.LinkedList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public class GameHMI extends StackPane {

	private Canvas canvas;
	private List<GameInterfaceLayer> gameInterfaceLayers;
	
	public GameHMI(int width, int height) {
		gameInterfaceLayers = new LinkedList<GameInterfaceLayer>();
		this.setWidth(width);
		this.setHeight(height);
		this.canvas = new Canvas(width, height);
		this.getChildren().add(canvas);
		
		
	}

	public GraphicsContext getCanvasContext() {
		return canvas.getGraphicsContext2D();
	}
	
	public void registerGameInterfaceLayer(GameInterfaceLayer gil) {
		this.gameInterfaceLayers.add(gil);
		this.getChildren().add(gil);
	}
	
	public GameInterfaceLayer getGameInterfaceLayer(String uid) {
		for(GameInterfaceLayer gil : this.gameInterfaceLayers) {
			System.out.println(gil);
			if(gil.getUid().equals(uid)) {
				return gil;
			}
		}
		return null;
	}
}