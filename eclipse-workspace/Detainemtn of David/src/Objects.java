import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Objects extends GameObject {
	boolean isAlive;

	Objects(float x, float y, Image im) {
		super(x, y, im);
		isAlive = true;
	}

	abstract void update(GameContainer gc);

	abstract void draw(Graphics g);

}

class ladder extends Objects {
	String s;
	ladder(float x, float y, Image im) {
		super(x, y, im);
	}

	public void update(GameContainer gc) {
		if (collision(Engine.p.getX(), Engine.p.getX() + 50, Engine.p.getY(), Engine.p.getY() + 50)) {
			Engine.level = Engine.level+1;
			s = "data/level";
			s = s + " " + Engine.level + ".xls";
			Engine.setXls(s);
			Engine.rooms();
			isAlive = false;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(im, x, y);
	}
}

class Heart extends Objects {

	Heart(float x, float y, Image im) {
		super(x, y, im);
	}

	public void update(GameContainer gc) {
		if (collision(Engine.p.getX(), Engine.p.getX() + 50, Engine.p.getY(), Engine.p.getY() + 50)
				&& Engine.p.getCurH() != Engine.p.getMaxH()) {
			Engine.p.setCurH(Engine.p.getCurH() + 1);
			isAlive = false;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(im, x, y);
	}
}

class Doors extends Objects {

	Doors(float x, float y, Image im) {
		super(x, y, im);
	}

	public void update(GameContainer gc) {
	if (Engine.rooms[Engine.p.getRoomX()][Engine.p.getRoomY()].isClear) {
			if (collision(Engine.p.getX(), Engine.p.getX() + 50, Engine.p.getY(), Engine.p.getY() + 50)) {
				if (Engine.p.getDirectionM() == "up" && Engine.p.getY() < 100) {
					Engine.p.setY(Engine.height - 76 - 50);
					Engine.p.moveRoom(0, -1);
					Engine.p.projectiles.clear();
					Engine.p.setOriginY(Engine.p.getOriginY()+19);
				} else if (Engine.p.getDirectionM() == "right" && Engine.p.getX() > Engine.width - 150) {
					Engine.p.setX(76);
					Engine.p.moveRoom(1, 0);
					Engine.p.projectiles.clear();
					Engine.p.setOriginX(Engine.p.getOriginX()-19);
				} else if (Engine.p.getDirectionM() == "down" && Engine.p.getY() > Engine.height - 150) {
					Engine.p.setY(76);
					Engine.p.moveRoom(0, 1);
					Engine.p.projectiles.clear();
					Engine.p.setOriginY(Engine.p.getOriginY()-19);
				} else if (Engine.p.getDirectionM() == "left" && Engine.p.getX() < 100) {
					Engine.p.setX(Engine.width - 75 - 50);
					Engine.p.moveRoom(-1, 0);
					Engine.p.projectiles.clear();
					Engine.p.setOriginX(Engine.p.getOriginX()+19);
				}
			}
		}
	}

	public void draw(Graphics g) {
		g.drawImage(im, x, y);
	}
}
