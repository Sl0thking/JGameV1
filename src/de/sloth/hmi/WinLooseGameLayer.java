package de.sloth.hmi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.event.GameEvent;
import de.sloth.event.RestartEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class WinLooseGameLayer extends GameInterfaceLayer {

	@FXML
	private Button restartBtn;
	
	public WinLooseGameLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(uid, eventQueue);
		BorderPane pane = null;
		try {
			pane = (BorderPane) this.loadInterfaceLayerFXML();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setVisible(false);
		this.setDisable(true);
		this.getChildren().add(pane);
	}
	
	@FXML
	public void restartGame() {
		GameEvent restartEvent = new RestartEvent();
		getEventQueue().add(restartEvent);
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}
}