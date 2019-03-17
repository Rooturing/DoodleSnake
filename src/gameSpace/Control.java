package gameSpace;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import field.Field;
import field.View;
import snake.Head;
import snake.Snake;
import snake.Box;
import snake.Food;

public class Control{
	
	private static HashMap<Integer, String> directionMap = new HashMap<Integer, String>();
	private JFrame frame;
	private Field field;
	private View view;
	
	public Control() {	
		
		directionMap.put(0, "north");
		directionMap.put(1, "south");
		directionMap.put(2, "west");
		directionMap.put(3, "east");
		
		//创建field
		int width = 25;
		int height = 25;
		field = new Field(width, height);
		
		//创建蛇
		Snake snake = initSnake();
		putSnake(snake);
		//创建食物
		Food food = initFood();
				
		view = new View(field);
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Doodel Snakes");
		frame.add(view);
		frame.pack();
		frame.setVisible(true);	
		
		//监听键盘操作，控制蛇向左或者向右运动
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_LEFT) {
						snake.turn(true);
						putSnake(snake);
						System.out.println("left");
					}else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
						snake.turn(false);	
						putSnake(snake);
						System.out.println("rigth");
					}										
				}
			});
		
			//先判断一次蛇是否在field内
			boolean in = inField(snake.getHead().getX(),snake.getHead().getY());
			while(in) {
				//判断蛇是否吃到了食物
				if(snake.getHead().getX()==food.getX()&&snake.getHead().getY()==food.getY()) {
					snake.eatFood(food);
					putSnake(snake);
					food.remove();
					food = initFood();
				}else {
					field.place(food.getX(), food.getY(), food);
				}
				view.repaint();
				
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					
				snake.autoMove();
				//再进行一次判断
				in = inField(snake.getHead().getX(),snake.getHead().getY());
				if(!in) {
					break;
				}
				putSnake(snake);
				
				
			}
			
			System.out.println("Game over!");
			
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//					
		
			
//		}
		
//		System.out.println(snake.getHead().getDirection());
		
////调试		
//		Box box = snake.popElement();
//		int x = box.getX()+1;
//		int y = box.getY()+1;
//		Box box = (Box)snake.get(1);
//		int x = box.getX()+1;
//		int y = box.getY()+1;
//		String direction = head.getDirection();
//		System.out.println("("+x+","+y+"):"+snake.getLength()+":"+direction);
//		System.out.println("("+x+","+y+")");
//		Box box1 = snake.popElement();
//		int x1 = box1.getX()+1;
//		int y1 = box1.getY()+1;
//		System.out.println("("+x1+","+y1+")");
//		Box box2 = snake.popElement();
//		int x2 = box2.getX()+1;
//		int y2 = box2.getY()+1;
//		System.out.println("("+x2+","+y2+")");
		
////
		
//		start.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				System.out.println("let's begin!");
//				play(snake, frame);
//				frame.repaint();
//				
//			}
//		});
		

		
		
	}
	
	public void putSnake(Snake snake) {
		
		//field初始化
		for(int x=0; x<field.getWidth(); x++) {
			for(int y=0; y<field.getHeight(); y++) {
			field.place(x, y, new Box(x, y));
			}
		}
		
		for(int i=0; i<snake.getLength(); i++) {
			Box box = snake.getElement(i);
			int x = box.getX();
			int y = box.getY();
			field.place(x, y, box);
		}
	}
	
	
	public Snake initSnake() {
		int width = field.getWidth();
		int height = field.getHeight();
		Random rand = new Random();
		int x = rand.nextInt(width);
		int y = rand.nextInt(height);
		String dir = directionMap.get(rand.nextInt(4));
		Head head = new Head(x, y, dir);
		int length = rand.nextInt(width<=height?width:height)/2+1;
		
		LinkedList<Box> list = new LinkedList<Box>();
		int len = 0;
		Box box = new Box(x, y);
			
		for(int i=0; i<length; i++) {				
			//判断是否在显示区内
			if(inField(x, y)) {	
				box.set();
				list.add(box);
				len++;
			}else {
				break;	
			}
			//根据蛇头方向判断初始的蛇该怎么画
			if(dir=="south") {
				box = new Box(x, --y);
			}else if(dir=="north") {
				box = new Box(x, ++y);
			}else if(dir=="west") {
				box = new Box(++x, y);
			}else if(dir=="east"){
				box = new Box(--x, y);
			}
			
			
		}
		Snake snake = new Snake(head, len, list);
		return snake;
	}
	
	public Food initFood() {
		Random rand = new Random();
		int foodX = rand.nextInt(field.getWidth());
		int foodY = rand.nextInt(field.getHeight());
		Food food = new Food(foodX, foodY);
		food.set();
		field.place(foodX, foodY, food);
		return food;
		
	}
	
	public boolean inField(int x, int y) {
		boolean flag = true;
		if(x<0 || x>=field.getWidth() || y<0 || y>=field.getHeight()) {
			flag = false;
		}
		return flag;
	}
			
	
	
	public void snakeEatsFood(Snake snake, Food food) {
		
		
		
		
		
		
		
	}
		
		
		
		
		
	
	

	

	public static void main(String[] args) {
		
		Control control = new Control();
		
		
		
//		int x = snake.getHead().getX()+1;
//		int y = snake.getHead().getY()+1;
//		int length = snake.getLength();
//		String direction = snake.getHead().getDirection();
//		System.out.println("("+x+","+y+"),"+length+","+direction);
		
		//画图
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	

}
