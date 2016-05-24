package de.sloth.system;

import java.util.Scanner;

import de.sloth.component.Position2DComp;

public class ControllSystem extends GameSystem {

	@Override
	public void run() {
		while(!this.isInterrupted()) {
			Scanner scan = new Scanner(System.in);
			String line = scan.nextLine();
			while(line == null){}
			if(line.equals("d")) {
				Position2DComp comp = (Position2DComp) this.getEntities().get(0).getComponents(Position2DComp.class).get(0);
				comp.setX(comp.getX()+1);
			}
		}
	}

	
}
