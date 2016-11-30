package de.sloth.component;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class LivingComp extends Component {
	private boolean isLiving;
	private SimpleIntegerProperty hp;
	private SimpleIntegerProperty hpMax;
	private int attack;
	private int defense;

	public int getHp() {
		return hp.getValue();
	}

	public void setHp(int hp) {
		this.hp.setValue(hp);
	}

	public int getHpMax() {
		return hpMax.getValue();
	}

	public void setHpMax(int hpMax) {
		this.hpMax.setValue(hpMax);
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public LivingComp(boolean isLiving) {
		super();
		this.isLiving = isLiving;
		this.hp = new SimpleIntegerProperty();
		this.hp.setValue(10);
		this.hpMax = new SimpleIntegerProperty();
		this.hpMax.setValue(10);
		this.attack = 5;
		this.defense = 2;
	}

	public boolean isLiving() {
		return isLiving;
	}

	public void setLiving(boolean isLiving) {
		this.isLiving = isLiving;
	}

	@Override
	public String toString() {
		return "LivingComp [isLiving=" + isLiving + ", hp=" + hp + ", hpMax=" + hpMax + ", attack=" + attack
				+ ", defense=" + defense + "]";
	}

	public ObservableValue<? extends Number> getHpProperty() {
		// TODO Auto-generated method stub
		return hp;
	}

	public ObservableValue<? extends Number> getHpMaxProperty() {
		// TODO Auto-generated method stub
		return hpMax;
	}
	
	
}
