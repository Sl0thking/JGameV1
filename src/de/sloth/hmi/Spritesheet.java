package de.sloth.hmi;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class Spritesheet extends Image {
	
	public Spritesheet(String url) {
		super(url);
	}
	
	public Spritesheet(String string, double d, double e, boolean b, boolean c) {
		super(string, d, e, b, c);
	}

	public Image splice(int startX, int startY, int width, int height) {
		System.out.println("STARTX: " + startX + " STARTY: " + startY);
		PixelReader pr = this.getPixelReader();
		WritableImage outputImage = new WritableImage(width, height);
		PixelWriter wr = outputImage.getPixelWriter();
		for(int y = startY; y < startY+height; y++) {
			for(int x = startX; x < startX+width; x++) {
				wr.setColor(x-startX, y-startY, pr.getColor(x, y));
			}
		}
		return outputImage;
	}
}