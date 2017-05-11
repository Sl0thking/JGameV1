package de.sloth.system.game.soundSystem;

import java.io.File;
import java.io.FilenameFilter;

import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class PlayBgm implements IBehavior {

	private MediaPlayer player;
	private int counter;
	private int currentSongCount;
	
	public PlayBgm() {
		counter = 0;
		File bgmFolder = new File("./bg");
		currentSongCount = bgmFolder.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.contains("song_") && name.contains(".mp3");
			}
		}).length;
	}
	
	@Override
	public void execute(GameSystem system) {
		if( player == null || player.getStatus().equals(Status.STOPPED))  {
			this.changeSong();
			player.play();
		} else if(player.getStatus().equals(Status.HALTED) || player.getStatus().equals(Status.READY)) {
			player.play();
			
		}
	}
	
	private void changeSong() {
		this.counter++;
		if(this.counter > this.currentSongCount) {
			this.counter = 1;
		}
		Media nextSong = new Media(new File("bg/song_" + this.counter + ".mp3").toURI().toString());
		player = new MediaPlayer(nextSong);
		player.setVolume(0.7);
		player.setOnEndOfMedia(new Runnable() {	
			@Override
			public void run() {
				player.stop();	
			}
		});
	}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {}
}
