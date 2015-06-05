package ie.tcd.gourleys;

import java.io.FileNotFoundException;

import ie.tcd.gourleys.entities.Playable;
import ie.tcd.gourleys.maps.MapMaker;
import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")
public class Main extends PApplet {
	
	public static final int CANVAS_WIDTH = 300;
	public static final int CANVAS_HEIGHT = 300;
	public static final int TILE_ROW = 20;
	public static final int TILE_COL = 20;
	MapMaker map;
	Playable player;
	int[][] indexes = 	{{3, 3, 2, 3, 3},
						{0, 1, 0, 0, 3},
						{6, 6, 7, 1, 2},
						{1, 0, 8, 6, 7},
						{3, 3, 3, 2, 11}};

	
	public void setup() {
		map = new MapMaker(this, CANVAS_WIDTH, CANVAS_HEIGHT, TILE_ROW, TILE_COL);
		PImage texture = loadImage("img/pkmn/base_grass.png");
		map.slice(texture, 2, 3);
		map.randomBase();
		
		PImage[] character = {loadImage("img/nekid/nekid_down.png"), loadImage("img/nekid/nekid_up.png"),
							loadImage("img/nekid/nekid_right.png"), loadImage("img/nekid/nekid_left.png")};
		player = new Playable(this, character, CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
//		player.resize(CANVAS_WIDTH, CANVAS_HEIGHT, TILE_ROW, TILE_COL);
		
		size(CANVAS_WIDTH, CANVAS_HEIGHT);
	}
	
	public void draw() {
		map.draw();
		player.draw();
	}
	
	public void keyPressed() {
		player.keyPressed();
	}
	
	public void keyReleased() {
		player.keyReleased();
	}

}
