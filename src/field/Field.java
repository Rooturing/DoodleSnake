package field;

import java.util.ArrayList;

import snake.Box;
import snake.Snake;

public class Field {

	private int width;
	private int height;
	private Box[][] field;
	
	public Field(int width, int height) {
		this.width = width;
		this.height = height;
		field = new Box[width][height];
	}

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void place(int x, int y, Box box) {
		field[x][y] = box;
	}
	public Box getBox(int x, int y) {
		return field[x][y];
	}
	
	public Box[] getSnake() {
		ArrayList<Box> list = new ArrayList<Box>();
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				if(field[i][j].isHere()) {
					list.add(field[i][j]);
				}
			}
		}
		return list.toArray(new Box[list.size()]);
	}
	
	
	
	
}
