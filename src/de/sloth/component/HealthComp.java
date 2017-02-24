package de.sloth.component;

import de.sloth.component.Component;
import javafx.beans.property.SimpleIntegerProperty;

public class HealthComp extends Component {
	private SimpleIntegerProperty lifes;

	public HealthComp(int lifes) {
		super();
		this.lifes = new SimpleIntegerProperty(lifes);
	}

	public int getLifes() {
		return lifes.get();
	}

	public void setLifes(int lifes) {
		this.lifes.set(lifes);
	}	

	public SimpleIntegerProperty getLifeProperty() {
		return this.lifes;
	}
}