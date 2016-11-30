package de.sloth.hmi;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameHMI extends StackPane {
	private Label label;
	private GameBar hBar;
	private GameBar eBar;
	private Canvas canvas;
	
	public GameHMI(int width, int height, int spriteWidth, int spriteHeight) {
		this.setWidth(width);
		this.setHeight(height);
		this.canvas = new Canvas(width, height);
		this.getChildren().add(canvas);
		BorderPane pane = new BorderPane();
		label = new Label("Test");
		label.setTextFill(Color.WHITE);
		hBar = new GameBar("red");
		eBar = new GameBar("orange");
		VBox vbox = new VBox();
		vbox.getChildren().add(hBar);
		vbox.getChildren().add(eBar);
		pane.setTop(label);
		pane.setBottom(vbox);
		this.getChildren().add(pane);
	}
	
	public Label getLabel() {
		return label;
	}

	public GameBar gethBar() {
		return hBar;
	}
	
	public GameBar getEBar() {
		return eBar;
	}

	public void sethBar(GameBar hBar) {
		this.hBar = hBar;
	}

	public GraphicsContext getCanvasContext() {
		return canvas.getGraphicsContext2D();
	}
	
	
}
