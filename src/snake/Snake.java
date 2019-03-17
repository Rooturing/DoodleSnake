package snake;

import java.util.LinkedList;

public class Snake extends LinkedList{

	private int length;
	private Head head;
	private LinkedList<Box> snake = new LinkedList<Box>();
	
	public Snake(Head head, int length, LinkedList<Box> snake) {
		this.length = length;
		this.head = head;
		this.snake = snake;
	}			
	


	public int getLength() {
		return length;
	}	
	public void addLength() {
		length++;
	}
	public Head getHead() {
		return head;
	}
	
	public Box getElement(int i) {
		return snake.get(i);
	}
	
	public void eatFood(Food food) {
		snake.addFirst(food);	
		snake.getFirst().set();
		length++;
	}
	
	private LinkedList<Box> moveBody(LinkedList<Box> newSnake) {
		//�ƶ�����
		for(int i=1; i<length; i++) {
			Box frontBox = snake.get(i-1);
			int frontX = frontBox.getX();
			int frontY = frontBox.getY();
			Box newBox = new Box(frontX, frontY);
			newBox.set();
			newSnake.add(newBox);
		}
		return newSnake;
	}
	
	public void autoMove() {
		//�½�һ����
		LinkedList<Box> newSnake = new LinkedList<Box>();
		//��ȡ��ͷ��λ�ã�Ȼ���ƶ���ͷ
		int x = head.getX();
		int y = head.getY();
		String direction = head.getDirection();
		switch(direction) {
		case "north" : y-=1;break;
		case "south" : y+=1;break;
		case "east" : x+=1;break;
		case "west" : x-=1;break;
		}
		Head newHead = new Head(x, y, direction);
		newHead.set();
		head = newHead;
		newSnake.add(newHead);
		//�ƶ����岿��
		snake = moveBody(newSnake);
		
	}
	
	public void turn(boolean isLeft) {
		//�½�һ����
		LinkedList<Box> newSnake = new LinkedList<Box>();
		//��ȡ��ͷ�����λ�ò����ƶ���ͷ
		String direction = head.getDirection();
		int x = head.getX();
		int y = head.getY();
		switch(direction) {
		//���������ת��isLeftΪ1������Ϊ0
		case "north" : 			
			if(isLeft) {x-=1;direction = "west";}
			else {x+=1;direction = "east";}
			break; 
		case "south" : 
			if(isLeft) {x-=1;direction = "west";}
			else {x+=1;direction = "east";}
			break;
		case "east" : 
			if(isLeft) {y-=1;direction = "north";}
			else {y+=1;direction = "south";}
			break;
		case "west" : 
			if(isLeft) {y+=1;direction = "south";}
			else {y-=1;direction = "north";}
			break;
		}
		Head newHead = new Head(x, y, direction);
		newHead.set();
		head = newHead;
		newSnake.add(newHead);
		//�ƶ����岿��
		snake = moveBody(newSnake);
	}
	
	
}







