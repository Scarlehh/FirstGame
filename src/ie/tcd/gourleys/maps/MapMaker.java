package ie.tcd.gourleys.maps;

import processing.core.PApplet;
import processing.core.PImage;


public class MapMaker {
	
	PApplet parent;
	private PImage[] tiles;
	private int tileWidth;
	private int tileHeight;
	private int rows;
	private int cols;
	private int[][] index;
	
	public MapMaker(PApplet parent, int canvasWidth, int canvasHeight, int rows, int cols) {
		this.parent = parent;
		this.rows = rows;
		this.cols = cols;
		tileWidth = canvasWidth/cols;
		tileHeight = canvasHeight/rows;
	}
	
	public void slice(PImage texture, int rows, int cols) {
		tiles = new PImage[rows*cols];
		int tileWidth = texture.width / cols;
		int tileHeight = texture.height / rows;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				tiles[(i*cols) + j] = texture.get(tileWidth*j, tileHeight*i, tileWidth, tileHeight);
				tiles[(i*cols) + j].resize(this.tileWidth, this.tileHeight);
			}
		}
	}
	
	public void randomTiles() {
		index = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				index[i][j] = (int) parent.random(tiles.length);
			}
		}
	}
	
	public void setTiles(int[][] index) {
		this.index = index;
	}
	
	public PImage getTile(int index) {
		return tiles[index];
	}
	
	public int getWidth() {
		return tileWidth;
	}
	
	public int getHeight() {
		return tileHeight;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public void draw() {
		for (int i = 0; i < index.length; i++) {
			for (int j = 0; j < index[0].length; j++) {
				parent.image(getTile(index[i][j]), tileWidth * j, tileHeight * i);
			}
		}
	}
	
}
