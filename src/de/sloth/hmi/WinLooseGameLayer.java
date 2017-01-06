package de.sloth.hmi;

import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.event.GameEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WinLooseGameLayer extends GameInterfaceLayer {
	private Label label;
	private Button restart;
	
	public WinLooseGameLayer(String uid, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(uid, eventQueue);
		BorderPane pane = new BorderPane();
		label = new Label("You Died");
		restart = new Button("Restart");
		restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GameEvent restartEvent = new RestartEvent();
				getEventQueue().add(restartEvent);
			}
		});
		label.setFont(Font.font("Arial", 64));
		label.setTextFill(Color.WHITE);
		this.setVisible(false);
		this.setDisable(true);
		pane.setTop(label);
		pane.setCenter(restart);
		this.getChildren().add(pane);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public void setIsWin(boolean isWin) {
		if(isWin) {
			label.setText("WIN");
		} else {
			label.setText("Loose");
		}
	}
}