package de.sloth.system.game;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.sloth.entity.Entity;
import de.sloth.event.GameEvent;
import de.sloth.system.GameSystem;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class BGMusicSystem extends GameSystem {
	MediaPlayer mPlayer;
	
	public BGMusicSystem() {
		super();
		Media bgm = new Media(new File("bg/game_bgm.mp3").toURI().toString());
		mPlayer = new MediaPlayer(bgm);
	}

	public BGMusicSystem(ConcurrentLinkedQueue<Entity> entities, ConcurrentLinkedQueue<GameEvent> eventQueue) {
		super(entities, eventQueue);
		Media bgm = new Media(new File("bg/game_bgm.mp3").toURI().toString());
		mPlayer = new MediaPlayer(bgm);
	}

	@Override
	public void executeSystem() {
		System.out.println(mPlayer.getStatus().toString());
		if(mPlayer.getStatus().equals(Status.HALTED) || mPlayer.getStatus().equals(Status.STOPPED) || mPlayer.getStatus().equals(Status.READY)) {
			//mPlayer.play();
		}
	}
	
	
	
}
