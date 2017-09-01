package de.sloth.core.main.system;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import de.sloth.hmi.FPSCalculator;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Main Game Loop, basing on a animation timer
 * 
 * @author Kevin Jolitz
 * @version 1.0.1
 * @date 18.05.2017
 *
 */
public class GameCore extends AnimationTimer {

	private List<DefaultGameSystem> gameSystems;
	private int ms_per_frame; // = 1000/60;
	private FPSCalculator fpsCalc;
	private boolean isCapped;
	private int loopSpeedMod;

	public GameCore() {
		this.gameSystems = new LinkedList<DefaultGameSystem>();
		this.ms_per_frame = 1000/60;
		this.fpsCalc = new FPSCalculator();
		this.isCapped = true;
		this.loopSpeedMod = 1;
	}
	
	public GameCore(int loopSpeedMod) {
		this.gameSystems = new LinkedList<DefaultGameSystem>();
		this.ms_per_frame = 1000/60;
		this.fpsCalc = new FPSCalculator();
		this.isCapped = true;
		this.loopSpeedMod = loopSpeedMod;
	}

	public GameCore(int loopSpeedMod, int fps) {
		this.gameSystems = new LinkedList<DefaultGameSystem>();
		this.ms_per_frame = 1000/fps;
		this.fpsCalc = new FPSCalculator();
		this.isCapped = true;
		this.loopSpeedMod = loopSpeedMod;
	}
	
	public GameCore(int loopSpeedMod, int fps, boolean isCapped) {
		this.gameSystems = new LinkedList<DefaultGameSystem>();
		this.ms_per_frame = 1000/fps;
		this.fpsCalc = new FPSCalculator();
		this.isCapped = isCapped;
		this.loopSpeedMod = loopSpeedMod;
	}	
	
	public SimpleIntegerProperty getFpsProperty() {
		return this.fpsCalc.getFpsProperty();
	}
	
	public void registerSystem(DefaultGameSystem system) {
		gameSystems.add(system);
	}
	
	public void removeSystem(DefaultGameSystem system) {
		gameSystems.remove(system);
	}
	
	public void doGameLogic() throws Exception {
		for(DefaultGameSystem system : gameSystems) {
			system.executeSystem();
		}
	}

	@Override
	public void handle(long now) {
		//Normally input is recognized here, but everything is handled by fx so ignored in gameloop
		//handleUserInput();
		double secondsBefore = new Date().getTime()/1000.0;
		this.fpsCalc.start();
		for(int i = 0; i < this.loopSpeedMod; i++) {
			try {
				this.doGameLogic();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		double secondsAfter = new Date().getTime()/1000.0;
		if(isCapped) {
			try {
				long wait = (long) ((secondsBefore*1000 + ms_per_frame) - secondsAfter*1000);
				if(wait >= 0) {
					Thread.sleep(wait);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.fpsCalc.stop();
	}

	public List<DefaultGameSystem> getRegistredSystems() {
		return gameSystems;
	}

	@Override
	public String toString() {
		String coreStr = "GameCore [ms_per_frame=" + ms_per_frame + ", fpsCapped=" + isCapped + ", loopSpeed="+ loopSpeedMod + "]\n";
		for(DefaultGameSystem sys : gameSystems) {
			coreStr += "\tRegistred system [" + sys.toString() + "]\n";
		}
		return coreStr;
	}
}