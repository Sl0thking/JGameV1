package de.sloth.hmi;

import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GameHMI extends StackPane {
	private Label label;
	
	public GameHMI(double width, double heigth, LayeredFieldCanvasPane main) {
		this.setWidth(width);
		this.setHeight(heigth);
		this.getChildren().add(main);
		BorderPane pane = new BorderPane();
		label = new Label("Test");
		label.setTextFill(Color.WHITE);
		pane.setTop(label);
		this.getChildren().add(pane);
	}
	
	public Label getLabel() {
		return label;
	}
}
