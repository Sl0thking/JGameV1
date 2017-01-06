package de.sloth.hmi;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.event.GameEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class InventoryGameLayer extends GameInterfaceLayer {
	Label inventoryLabel;
	
	public InventoryGameLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(uid, eventQueue);
		this.inventoryLabel = new Label("INVENTORY");
		this.getChildren().add(inventoryLabel);
		this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setVisible(false);
		this.setDisable(true);
	}

}
