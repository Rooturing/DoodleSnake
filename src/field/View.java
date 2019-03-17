package field;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import snake.Box;

public class View extends JPanel{
	
	private static final int GRID_SIZE = 25;
	private Field field;
	
	public View(Field field) {
		this.field = field;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		for(int x=0; x<field.getWidth(); x++) {
			for(int y=0; y<field.getHeight(); y++) {
				Box box = field.getBox(x, y);
				box.draw(g, x*GRID_SIZE, y*GRID_SIZE, GRID_SIZE);
			}
		}
		
	}
	
	public Dimension getPreferredSize() {		
		return new Dimension(field.getWidth()*GRID_SIZE+5, field.getHeight()*GRID_SIZE+5);	
	}
	
	
	public static void main(String[] args) {
		Field field = new Field(30,30);		
		
		for(int x=0; x<field.getWidth(); x++) {
			for(int y=0; y<field.getHeight(); y++) {
				field.place(x, y, new Box(x, y));
			}
		}
		
		Box box = field.getBox(20, 20);
		box.set();


		View view = new View(field);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setName("Doodle Snake");
		frame.add(view);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
