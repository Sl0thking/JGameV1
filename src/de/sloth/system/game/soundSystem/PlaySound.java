package de.sloth.system.game.soundSystem;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class PlaySound implements IBehavior {

	private double seVolume = 0.0;
	
	public PlaySound() {
		this.seVolume = 0.5;
	}
	
	public PlaySound(double seVolume) {
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