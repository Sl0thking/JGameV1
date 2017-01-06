package de.sloth.hmi;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.event.GameEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PlayerInformationLayer extends GameInterfaceLayer {
	private GameBar hBar;
	private GameBar eBar;
	
	public PlayerInformationLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(uid, eventQueue);
		BorderPane pane = new BorderPane();
		hBar = new GameBar("red");
		eBar = new GameBar("orange");
		VBox vbox = new VBox();
		vbox.getChildren().add(hBar);
		vbox.getChildren().add(eBar);
		pane.setBottom(vbox);
		this.getChildren().add(pane);
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
}