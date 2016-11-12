package de.sloth.component;

public class LivingComp extends Component {
	private boolean isLiving;
	private int hp;
	private int hpMax;
	private int attack;
	private int defense;

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
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
		this.hp = 10;
		this.hpMax = 10;
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
	
	
}
