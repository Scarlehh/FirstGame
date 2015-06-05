package ie.tcd.gourleys.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;


public class MapMaker {
	
	PApplet parent;
	private PImage[] tiles;
	private int tileWidth;
	private int tileHeight;
	private int rows;
	private int cols;
	private PImage[][] indexBase;
	private PImage[][] indexObstacles;
	
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
	
	public void randomBase() {
		indexBase = new PImage[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				indexBase[i][j] = tiles[(int) parent.random(tiles.length)];
			}
		}
	}
	
	public void setBase(String filepath) {
		indexBase = setTiles(filepath, indexBase);
	}
	
	public void setBase(File file) {
		indexBase = setTiles(file, indexBase);
	}
	
	public void setObstacles(String filepath) {
		indexObstacles = setTiles(filepath, indexObstacles);
	}
	
	public void setObstacles(File file) {
		indexObstacles = setTiles(file, indexObstacles);
	}
	
	public void setTiles(int[][] index) {
		indexBase = new PImage[index.length][index[0].length];
		for (int i = 0; i < index.length; i++) {
			for (int j = 0; j < index[0].length; j++) {
				indexBase[i][j] = tiles[index[i][j]];
			}
		}
	}
	
	public PImage[][] setTiles(File file, PImage[][] indexArray) {
		try {
			Scanner readRows = new Scanner(file);
			int rows = 0;
			while (readRows.hasNextLine()) {
				rows++;
				readRows.nextLine();
			}
			readRows.close();
			
			Scanner readCols = new Scanner(file);
			int cols = 0;
			while (readCols.hasNextInt()) {
				cols++;
				readCols.nextInt();
			}
			cols /= rows;
			readCols.close();
			
			indexArray = new PImage[rows][cols];
			Scanner loadFile = new Scanner(file);
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					int tileIndex = loadFile.nextInt();
					indexArray[i][j] = tiles[tileIndex];
				}
			}
			loadFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("No file to set tile references from.");
		}
		return indexArray;
	}
	
	public PImage[][] setTiles(String filepath, PImage[][] indexArray) {
		File file = new File(filepath);
		return setTiles(file, indexArray);
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
		for (int i = 0; i < indexBase.length; i++) {
			for (int j = 0; j < indexBase[0].length; j++) {
				parent.image(indexBase[i][j], tileWidth * j, tileHeight * i);
			}
		}
	}
	
}
