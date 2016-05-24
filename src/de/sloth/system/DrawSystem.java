package de.sloth.system;

import java.io.IOException;

import de.sloth.component.Position2DComp;
import de.sloth.entity.Entity;

public class DrawSystem extends GameSystem {
	
	private String[][] field;
	private int width;
	private int height;
	
	public DrawSystem(int width, int height) {
		this.width = width;
		this.height = height;
		this.field = new String[width][height];
	}
	
	private void clearField() {
		for (int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				field[x][y] = "-";
			}
		}
	}
	
	private void drawField() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
		for (int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				System.out.print(field[x][y]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	@Override
	public void run() {
		while(!this.isInterrupted()) {
			clearField();
			for(Entity entity : this.getEntities()) {
				Position2DComp comp = (Position2DComp) entity.getComponents(Position2DComp.class).get(0);
				field[comp.getY()][comp.getX()] = "@";
			}
			drawField();
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}