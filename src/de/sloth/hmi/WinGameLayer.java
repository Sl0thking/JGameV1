package de.sloth.hmi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.event.GameEvent;
import de.sloth.event.HMIEvent;
import de.sloth.event.HMIKeyword;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class WinGameLayer extends GameInterfaceLayer {
	
	public WinGameLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(uid, eventQueue);
		BorderPane pane = null;
		try {
			pane = (BorderPane) this.loadInterfaceLayerFXML();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getChildren().add(pane);
		this.setVisible(false);
		this.setDisable(true);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
	
	@FXML
	public void toMenu() {
		HMIEvent event = new HMIEvent(HMIKeyword.showMenu);
		this.getEventQueue().add(event);
	}

}
