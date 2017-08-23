package de.sloth.core.sound.behavior;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import de.sloth.core.main.GameEvent;
import de.sloth.core.main.GameSystem;
import de.sloth.core.main.IBehavior;
import de.sloth.core.sound.event.PlaySoundEvent;

public class BPlaySound implements IBehavior {

	private double seVolume = 0.0;
	
	public BPlaySound() {
		this.seVolume = 0.5;
	}
	
	public BPlaySound(double seVolume) {
		this.seVolume = seVolume;
	}
	
	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		PlaySoundEvent pse = (PlaySoundEvent) expectedEvent;
		File soundFile = new File(".\\se\\" + pse.getDesiredSoundFileName() + ".mp3");
		if(soundFile.exists()) {
			Media nextSong = new Media(soundFile.toURI().toString());
			MediaPlayer player = new MediaPlayer(nextSong);
			player.setVolume(this.seVolume);
			player.play();
		}
	}
}