package de.sloth.core.neuralNetwork.event;

import de.sloth.core.main.GameEvent;

/**
 * Event to trigger behavior of the genetical system.
 * 
 * @author Kevin Jolitz
 * @version 1.0.0
 * @date 22.05.2017
 */
public class GeneticalEvent extends GameEvent {

	public GeneticalEvent(String string) {
		super(string);
	}
	
	public GeneticalEvent() {
		super();
	}
}