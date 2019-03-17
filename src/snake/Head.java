package snake;

public class Head extends Box{

	private String direction;
	
	public Head(int x, int y, String direction) {
		super(x, y);
		this.direction = direction;
	}
	
	
	public String getDirection() {
		return direction;
	}
	
	


	public void turn(int turn) {
		if(direction=="north") {
			x -= turn;
		}else if(direction=="south") {
			x += turn;
		}else if(direction=="west") {
			y -= turn;
		}else if(direction=="east") {
			y += turn;
		}
	}
	
}
