package de.sloth.core.sound.behavior;

import java.io.File;
import java.io.FilenameFilter;

import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class BPlayBgm implements IBehavior {

	private MediaPlayer player;
	private int counter;
	private int currentSongCount;
	private double bgmVolume;
	
	public BPlayBgm() {
		counter = 0;
		File bgmFolder = new File("./bg");
		currentSongCount = bgmFolder.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.contains("song_") && name.contains(".mp3");
			}
		}).length;
		this.bgmVolume = 0.5;
	}
	
	public BPlayBgm(double bgmVolume) {
		counter = 0;
		File bgmFolder = new File("./bg");
		currentSongCount = bgmFolder.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.contains("song_") && name.contains(".mp3");
			}
		}).length;
		this.bgmVolume = bgmVolume;
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
		player.setVolume(this.bgmVolume);
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
