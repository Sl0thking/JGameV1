package de.sloth.system.hmi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import de.sloth.hmi.Spritesheet;
import javafx.scene.image.Image;

public class SpriteLoader {
	
	private Map<String, Image> sprites;
	private static SpriteLoader instance;
	private int spriteWidth;
	private int spriteHeight;
	private final int SHEET_HEIGHT = 64;
	private final int SHEET_WIDTH = 128;
	private int animationPhases;
	private String[] animationNames;
	
	public static SpriteLoader getInstance(double scaling, int spriteWidth, int spriteHeight, int animationPhases, String[] animationNames) {
		if(instance == null) {
			instance = new SpriteLoader(scaling, spriteWidth, spriteHeight, animationPhases, animationNames);
		}
		return instance;
	}
	
	private SpriteLoader() {
		sprites = new HashMap<String, Image>();
		refresh(1.0);
	}
	
	private SpriteLoader(double scaling,  int spriteWidth, int spriteHeight, int animationPhases, String[] animationNames) {
		sprites = new HashMap<String, Image>();
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.animationPhases = animationPhases;
		this.animationNames = animationNames;
		refresh(scaling);
	}
	
	public void refresh(double scaling) {
		sprites.clear();
		loadSprites(scaling);
		loadSpritesheets(scaling);
	}
	
	private void loadSprites(double scaling) {
		File spritesDir = new File("./sprites");
		File[] spritesInDir = spritesDir.listFiles();
		for (File sprite : spritesInDir) {
			if(!sprite.isDirectory()) {
				double[] dim = getDimension(sprite);
				Image spriteAsImage = new Image(("file:" + sprite.getAbsolutePath()), dim[0]*scaling, dim[1]*scaling, true, false);
				sprites.put(sprite.getName(), spriteAsImage);
			}
		}
	}
	
	private double[] getDimension(File f) {
		Image coreImage = new Image("file:" + f.getAbsolutePath());
		double[] dim = {coreImage.getWidth(), coreImage.getHeight()};
		return dim;
		
	}
	
	private void loadSpritesheets(double scaling) {
		File spritesheetDir = new File("./spritesheets");
		File[] spritesheetsInDir = spritesheetDir.listFiles();
		for (File sprite : spritesheetsInDir) {
			if(!sprite.isDirectory()) {
				Spritesheet sheet = new Spritesheet(("file:" + sprite.getAbsolutePath()), SHEET_WIDTH*scaling, SHEET_HEIGHT*scaling, true, false);
				int animationPhaseIndex = 0;
				for(String animation_phase : animationNames) {
					for(int phase_nr = 0; phase_nr < animationPhases; phase_nr++) {
						int scaledSpriteWidth = (int) (spriteWidth*scaling);
						int scaledSpriteHeight = (int) (spriteHeight*scaling);
						int startY = (animationPhaseIndex*scaledSpriteHeight);
						int startX = (phase_nr*scaledSpriteWidth);
						sprites.put((sprite.getName() + "_" + animation_phase + "_" + phase_nr), sheet.splice(startX, startY, scaledSpriteWidth, scaledSpriteHeight));
					}
					animationPhaseIndex++;
				}
			}
		}
	}
	
	public Image getSprite(String name) {
		return this.sprites.get(name);
	}

}
