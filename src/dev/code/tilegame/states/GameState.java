package dev.code.tilegame.states;

import java.awt.Graphics;

import dev.code.tilegame.Handler;
import dev.code.tilegame.Game;
import dev.code.tilegame.entities.creatures.Player;
import dev.code.tilegame.gfx.Assets;
import dev.code.tilegame.tiles.Tile;
import dev.code.tilegame.worlds.World;

public class GameState extends State{
	
	private Player player;
	
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		player = new Player(handler, 100, 100);

		
		
		
	}

	@Override
	public void update() {
		world.update();
		player.update();

		
	}

	@Override
	public void render(Graphics g) {
	world.render(g);
	player.render(g);

	
		
	}
	
	

}
