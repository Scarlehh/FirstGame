package ie.tcd.gourleys.entities;

import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.core.PImage;

public class Playable {

	PApplet parent;
	public static final int LEFT = 3;
	public static final int RIGHT = 2;
	public static final int UP = 1;
	public static final int DOWN = 0;
	
	private PImage[] sprite;
	private int x;
	private int y;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private int spriteIndex;
	
	
	public Playable (PApplet parent, PImage[] sprite, int x, int y) {
		this.parent = parent;
		this.sprite = sprite;
		spriteIndex = 0;
		this.x = x;
		this.y = y;
	}
	
	public void resize(int canvasWidth, int canvasHeight, int rows, int cols) {
		int width = canvasWidth/cols;
		int height = canvasHeight/rows;
		for (int i = 0; i < sprite.length; i++) {
			sprite[i].resize(width, height);
		}
	}
	
	public void move() {
		if (left) {
			spriteIndex = LEFT;
			x--;
		}
		if (right) {
			spriteIndex = RIGHT;
			x++;
		}
		if (up) {
			spriteIndex = UP;
			y--;
		}
		if (down) {
			spriteIndex = DOWN;
			y++;
		}
	}
	
	public void keyPressed() {
		switch(parent.keyCode) {
		case PApplet.LEFT:
			left = true;
			break;
		case PApplet.RIGHT:
			right = true;
			break;
		case PApplet.UP:
			up = true;
			break;
		case PApplet.DOWN:
			down = true;
			break;
		}
	}

	public void keyReleased() {
		switch(parent.keyCode) {
		case PApplet.LEFT:
			left = false;
			break;
		case PApplet.RIGHT:
			right = false;
			break;
		case PApplet.UP:
			up = false;
			break;
		case PApplet.DOWN:
			down = false;
			break;
		}
	}

	
	public void setLeft(boolean state) {
		left = state;
	}
	
	public void setRight(boolean state) {
		right = state;
	}
	
	public void setUp(boolean state) {
		up = state;
	}
	
	public void setDown(boolean state) {
		down = state;
	}
	
	public void draw() {
		parent.image(sprite[spriteIndex], x, y);
		move();
	}
	
}
