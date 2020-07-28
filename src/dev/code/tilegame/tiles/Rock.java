package dev.code.tilegame.tiles;

import java.awt.image.BufferedImage;

import dev.code.tilegame.gfx.Assets;

public class Rock extends Tile {

	public Rock(int id) {
		super(Assets.stone, id);
		
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
