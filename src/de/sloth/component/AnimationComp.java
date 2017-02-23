package de.sloth.component;

public class AnimationComp extends Component {
	public String animationPhase;
	public int phaseNr;
	public int ticksForNextPhase;
	
	public AnimationComp(String animationPhase, int phaseNr, int ticksForNextPhase) {
		super();
		this.animationPhase = animationPhase;
		this.phaseNr = phaseNr;
		this.ticksForNextPhase = ticksForNextPhase;
	}

	public String getAnimationPhase() {
		return animationPhase;
	}

	public void setAnimationPhase(String animationPhase) {
		this.animationPhase = animationPhase;
	}

	public int getPhaseNr() {
		return phaseNr;
	}

	public void setPhaseNr(int phaseNr) {
		this.phaseNr = phaseNr;
	}

	public int getTicksForNextPhase() {
		return ticksForNextPhase;
	}

	public void setTicksForNextPhase(int ticksForNextPhase) {
		this.ticksForNextPhase = ticksForNextPhase;
	}
}
