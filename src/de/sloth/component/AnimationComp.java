package de.sloth.component;

public class AnimationComp extends Component {
	private String animationPhase;
	private int phaseNr;
	private int ticksForNextPhase;
	private int ticksForAnimation;
	private String stdAnimationPhase;
	
	public AnimationComp(String animationPhase, int phaseNr, int ticksForNextPhase) {
		super();
		this.animationPhase = animationPhase;
		this.setStdAnimationPhase(animationPhase);
		this.phaseNr = phaseNr;
		this.ticksForNextPhase = ticksForNextPhase;
		this.setTicksForAnimation(-1);
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

	public int getTicksForAnimation() {
		return ticksForAnimation;
	}

	public void setTicksForAnimation(int ticksForAnimation) {
		this.ticksForAnimation = ticksForAnimation;
	}

	public String getStdAnimationPhase() {
		return stdAnimationPhase;
	}

	public void setStdAnimationPhase(String stdAnimationPhase) {
		this.stdAnimationPhase = stdAnimationPhase;
	}
}
