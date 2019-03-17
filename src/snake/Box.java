package snake;

import java.awt.Graphics;

public class Box {
	
	protected boolean here = false;
	protected int x;
	protected int y;
	
	public Box(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
	public void set() {here = true;}
	public void remove() {here = false;}
	public boolean isHere() {return here;}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
		here = true;
	}
	
	public void draw(Graphics g, int x, int y, int size) {
		g.drawRect(x, y, size, size);
		if(here) {
			g.fillRect(x, y, size, size);
		}
	}
	
	
		
	
}
