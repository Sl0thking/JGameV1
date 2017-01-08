package de.sloth.hmi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.event.GameEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class InventoryGameLayer extends GameInterfaceLayer {
	Label inventoryLabel;
	
	public InventoryGameLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(uid, eventQueue);
		Pane pane = null;
		try {
			pane = (Pane) this.loadInterfaceLayerFXML();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getChildren().add(pane);
		this.setVisible(false);
		this.setDisable(true);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}

}
