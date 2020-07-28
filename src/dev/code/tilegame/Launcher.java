package dev.code.tilegame;

import dev.code.tilegame.display.Display;

public class Launcher {
	
	public static void main(String [] args) {
		
		Game game = new Game("Tiles!", 640, 360);
		game.start();
	}

}
