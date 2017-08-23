package de.sloth.core.main.component;

import javafx.beans.property.SimpleIntegerProperty;

public class ScoreComp extends Component {
	private SimpleIntegerProperty score;

	public ScoreComp(int score) {
		super();
		this.score = new SimpleIntegerProperty(score);
	}

	public int getScore() {
		return score.get();
	}

	public void setScore(int score) {
		this.score.set(score);
	}
	
	public SimpleIntegerProperty getScoreProperty() {
		return this.score;
	}
}