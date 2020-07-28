package dev.code.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32;
	private static final int height = 32;
	
	public static BufferedImage player, monster, grass, darkGrass, tree, stone;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spriteSheet.png"));
		
		player = sheet.crop(0, 0, width, height);
		grass = sheet.crop(0, 32, width, height);
		darkGrass = sheet.crop(width*3, 0, width, height);
		tree = sheet.crop(32, 32, width, height);
		stone = sheet.crop(width*2, 32, width, height);
	}

}
