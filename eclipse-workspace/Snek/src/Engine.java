
//David Morey
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Engine {

	public static Cell[][] cells = new Cell[v.MAP_WIDTH][v.MAP_HEIGHT];
	boolean isUp, isDown, isLeft, isRight;
	float timer = 0;

	public Engine() {

	}

	public boolean inBounds(int x, int y) {
		if (x >= 0 && x < v.MAP_WIDTH && y >= 0 && y < v.MAP_HEIGHT)
			return true;
		else
			return false;
	}

	public void reset() {
		v.maxLife = v.startingLife;
		isUp = false;
		isDown = false;
		isRight = false;
		isLeft = false;
		create();
	}

	public void makeF() {

		cells[(int) (Math.random() * v.MAP_WIDTH)][(int) (Math.random() * v.MAP_HEIGHT)].setiD(4);
	}

	public void create() {
		for (int i = 0; i < v.MAP_WIDTH; i++) {
			for (int j = 0; j < v.MAP_HEIGHT; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < v.MAP_WIDTH; i++) {
			for (int j = 0; j < v.MAP_HEIGHT; j++) {
				cells[i][j].draw(g);
			}
		}
	}



	public void update(GameContainer gc) {

		Input input = gc.getInput();
		timer++;

		if (input.isKeyDown(Input.KEY_UP) && isDown == false) {
			isUp = true;
			isDown = false;
			isLeft = false;
			isRight = false;
		}
		if (input.isKeyDown(Input.KEY_DOWN) && isUp == false) {
			isUp = false;
			isDown = true;
			isLeft = false;
			isRight = false;
		}
		if (input.isKeyDown(Input.KEY_LEFT) && isRight == false) {
			isUp = false;
			isDown = false;
			isLeft = true;
			isRight = false;
		}
		if (input.isKeyDown(Input.KEY_RIGHT) && isLeft == false) {
			isUp = false;
			isDown = false;
			isLeft = false;
			isRight = true;
		}
		if (timer % v.timer == 0) {
			for (int i = 0; i < v.MAP_WIDTH; i++) {
				for (int j = 0; j < v.MAP_HEIGHT; j++) {
					cells[i][j].update();
					if (cells[i][j].getiD() == 2) {
						
						
						if (isUp) {							
							cells[i][j].setiD(3);
							cells[i][j].setMaxD(v.maxLife);							
							if (inBounds(i, j - 1) && !cells[i][j - 1].isSnake()) {
								if (cells[i][j - 1].isFood()) {
									v.maxLife = v.maxLife + 2;
									makeF();
								}
								cells[i][j - 1].setiD(2);
							} else {
								reset();
							}
							return;
						}
						
						
						if (isDown) {							
							cells[i][j].setiD(3);
							cells[i][j].setMaxD(v.maxLife);							
							if (inBounds(i, j + 1) && !cells[i][j + 1].isSnake()) {
								if (cells[i][j + 1].isFood()) {
									v.maxLife = v.maxLife + 2;
									makeF();
								}
								cells[i][j + 1].setiD(2);
							} else {
								reset();
							}							
							return;
						}
						
						
						if (isLeft) {							
							cells[i][j].setiD(3);
							cells[i][j].setMaxD(v.maxLife);
							if (inBounds(i - 1, j) && !cells[i - 1][j].isSnake()) {
								if (cells[i - 1][j].isFood()) {
									v.maxLife = v.maxLife + 2;
									makeF();
								}
								cells[i - 1][j].setiD(2);
							} else {
								reset();
							}							
							return;
						}
						
						
						if (isRight) {						
							cells[i][j].setiD(3);
							cells[i][j].setMaxD(v.maxLife);						
							if (inBounds(i + 1, j) && !cells[i + 1][j].isSnake()) {
								if (cells[i + 1][j].isFood()) {
									v.maxLife = v.maxLife + 2;
									makeF();
								}
								cells[i + 1][j].setiD(2);
							} else {
								reset();
							}							
							return;
						}

					}
				}
			}
		}
	}
}
