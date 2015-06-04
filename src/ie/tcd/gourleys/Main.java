package ie.tcd.gourleys;

import ie.tcd.gourleys.maps.MapMaker;
import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")
public class Main extends PApplet {
	
	public static final int CANVAS_WIDTH = 1000;
	public static final int CANVAS_HEIGHT = 750;
	PImage texture;
	MapMaker grass;
	int[][] indexes = {{0, 1, 2, 2},
						{3, 4, 5, 4},
						{6, 7, 8, 6}};

	
	public void setup() {
		texture = loadImage("img/grass_texture.png");
		grass = new MapMaker(this, CANVAS_WIDTH, CANVAS_HEIGHT, 10, 10);
		grass.slice(texture, 6, 6);
//		grass.setTiles(indexes);
		grass.randomTiles();
		
		size(CANVAS_WIDTH, CANVAS_HEIGHT);
	}
	
	public void draw() {
		grass.draw();
	}

}
