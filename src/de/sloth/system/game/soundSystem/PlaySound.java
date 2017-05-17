package de.sloth.system.game.soundSystem;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import de.sloth.system.game.core.GameEvent;
import de.sloth.system.game.core.GameSystem;
import de.sloth.system.game.core.IBehavior;

public class PlaySound implements IBehavior {

	@Override
	public void execute(GameSystem system) {}

	@Override
	public void execute(GameSystem system, GameEvent expectedEvent) {
		PlaySoundEvent pse = (PlaySoundEvent) expectedEvent;
		File soundFile = new File(".\\se\\" + pse.getDesiredSoundFileName() + ".mp3");
		System.out.println(soundFile);
		if(soundFile.exists()) {
			System.out.println("SE EXISTS");
			Media nextSong = new Media(soundFile.toURI().toString());
			MediaPlayer player = new MediaPlayer(nextSong);
			player.play();
		}
	}
}