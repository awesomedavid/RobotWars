import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

abstract public class Rooms {
	public ArrayList<Enemies> enemy = new ArrayList<>();
	public ArrayList<Objects> objects = new ArrayList<>();
	public ArrayList<Upgrades> upgrades = new ArrayList<>();
	protected boolean isClear;
	protected int x, y, width, height;
	protected int thing, thing1, thing2;
	Color c;

	public Rooms(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		c = new Color(160, 75, 21);
	}

	public void update(GameContainer gc) {
	}

	public void draw(Graphics g) {
	}

	public boolean inBounds(int x, int y) {
		if (x >= 0 && x <= 10 && y >= 0 && y <= 10) {
			return true;
		} else {
			return false;
		}
	}
}

class basicRoom extends Rooms {
	int num = 1;
	int guy;
	public basicRoom(int x, int y, int width, int height) {
		super(x, y, width, height);
		guy = (int)(Math.random()*10);
		for (int i = 0; i < guy; i++) {
			enemy.add(new BasicE1((float) (Math.random() * (Engine.width - 75)),
					(float) (Math.random() * (Engine.height - 75)), Engine.skel));
		}
	}

	public void draw(Graphics g) {
		g.drawImage(Engine.wall_t, 0, 0);
		g.drawImage(Engine.wall_l, 0, 0);
		g.drawImage(Engine.wall_r, 775, 0);
		g.drawImage(Engine.wall_b, 0, 775);
		g.setColor(c);
		g.fillRect(75, 75, Engine.width - 150, Engine.height - 150);
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).draw(g);
		}
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(g);
		}
	}

	public void update(GameContainer gc) {
		if (enemy.size() > 0) {
			isClear = false;
		} else {
			isClear = true;
			num = 1;
			if (num == 1) {
				for (int i = 0; i < objects.size(); i++) {
					if (objects.get(i) instanceof Doors) {
						objects.remove(i);
					}
				}
			}
		}
		Image l = null, r = null, t = null, b = null;
		if (!(isClear)) {
			l = Engine.door_l_l;
			t = Engine.door_t_l;
			b = Engine.door_b_l;
			r = Engine.door_r_l;
		} else {
			if (inBounds(x - 1, y)) {
				if (x > 0 && Engine.levels[x - 1][y].equals("2")) {
					// left
					l = Engine.door_l;
				} else if (x > 0 && Engine.levels[x - 1][y].equals("3")) {
					l = Engine.door_l_u;
				} else if (x > 0 && Engine.levels[x - 1][y].equals("4")) {
					l = Engine.door_l_u;
				} else {
					l = null;
				}
			}
			if (inBounds(x, y - 1)) {
				if (y > 0 && Engine.levels[x][y - 1].equals("2")) {
					// up
					t = Engine.door_t;
				} else if (y > 0 && Engine.levels[x][y - 1].equals("3")) {
					t = Engine.door_t_u;
				} else if (y > 0 && Engine.levels[x][y - 1].equals("4")) {
					t = Engine.door_t_u;
				} else {
					t = null;
				}
			}
			if (inBounds(x + 1, y)) {
				if (x < Engine.levels.length - 1 && Engine.levels[x + 1][y].equals("2")) {
					// right
					r = Engine.door_r;
				} else if (x < Engine.levels.length - 1 && Engine.levels[x + 1][y].equals("3")) {
					r = Engine.door_r_u;
				} else if (x < Engine.levels.length - 1 && Engine.levels[x + 1][y].equals("4")) {
					r = Engine.door_r_u;
				} else {
					r = null;
				}
			}
			if (inBounds(x, y + 1)) {
				if (y < Engine.levels.length && Engine.levels[x][y + 1].equals("2")) {
					// down
					b = Engine.door_b;
				} else if (y < Engine.levels.length && Engine.levels[x][y + 1].equals("3")) {
					b = Engine.door_b_u;
				} else if (y < Engine.levels.length && Engine.levels[x][y + 1].equals("4")) {
					b = Engine.door_b_u;
				} else {
					b = null;
				}
			}
		}
		if (num == 1) {

			if (inBounds(Engine.p.getRoomX() - 1, Engine.p.getRoomY())
					&& !(Engine.levels[Engine.p.getRoomX() - 1][Engine.p.getRoomY()].equals("1"))) {
				objects.add(new Doors(0, Engine.height / 2 - (Engine.door_l.getHeight() / 2), l));
			}
			// right
			if (inBounds(Engine.p.getRoomX() + 1, Engine.p.getRoomY())
					&& !(Engine.levels[Engine.p.getRoomX() + 1][Engine.p.getRoomY()].equals("1"))) {
				objects.add(new Doors(Engine.width - Engine.door_r.getWidth(),
						Engine.height / 2 - (Engine.door_r.getHeight() / 2), r));
			}
			// top
			if (inBounds(Engine.p.getRoomX(), Engine.p.getRoomY() - 1)
					&& !(Engine.levels[Engine.p.getRoomX()][Engine.p.getRoomY() - 1].equals("1"))) {
				objects.add(new Doors(Engine.width / 2 - (Engine.door_t.getWidth() / 2), 0, t));
			}
			// bot
			if (inBounds(Engine.p.getRoomX(), Engine.p.getRoomY() + 1)
					&& !(Engine.levels[Engine.p.getRoomX()][Engine.p.getRoomY() + 1].equals("1"))) {
				objects.add(new Doors(Engine.width / 2 - (Engine.door_b.getWidth() / 2),
						Engine.width - Engine.door_b.getWidth(), b));
			}
			num = 0;
		}

		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).getCurH() <= 0) {
				if (Math.random() > .8)
					objects.add(new Heart(enemy.get(i).getX(), enemy.get(i).getY(), Engine.heart));
				enemy.remove(i);
			} else {
				enemy.get(i).update(gc);
			}
		}
		for (int i = 0; i < objects.size(); i++) {
			if (!(objects.get(i).isAlive)) {
				objects.remove(i);
			} else {
				objects.get(i).update(gc);
			}
		}
	}
}

class upgradeRoom extends Rooms {
	int num = 1;
	Upgrades u;
	ArrayList<Upgrades> Upgrades = new ArrayList<>();
	int rand;

	upgradeRoom(int x, int y, int width, int height) {
		super(x, y, width, height);
		u = new ExplosProjU(Engine.width / 2, Engine.height / 2, Engine.upgrade_ex);
		Upgrades .add(u);
		Upgrades s = Upgrades.get(rand);
		rand = (int) (Math.random() * Upgrades.size());
		objects.add(s);
	}

	public void draw(Graphics g) {
		g.setColor(new Color(255, 255, 0));
		g.fillRect(75, 75, Engine.width - 150, Engine.height - 150);
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(g);
		}
		for (int i = 0; i < upgrades.size(); i++) {
			upgrades.get(i).draw(g);
		}
	}

	public void update(GameContainer gc) {
		if (enemy.size() > 0) {
			isClear = false;
		} else {
			isClear = true;
		}
		Image l = null, r = null, t = null, b = null;

		if (num == 1) {
			if (x > 0 && Engine.levels[x - 1][y].equals("2")) {
				// left
				l = Engine.door_l;
			} else if (x > 0 && Engine.levels[x - 1][y].equals("3")) {
				l = Engine.door_l_u;
			} else if (x > 0 && Engine.levels[x - 1][y].equals("4")) {
				l = Engine.door_l_u;
			}

			if (y > 0 && Engine.levels[x][y - 1].equals("2")) {
				// up
				t = Engine.door_t;
			} else if (y > 0 && Engine.levels[x][y - 1].equals("3")) {
				t = Engine.door_t_u;
			} else if (y > 0 && Engine.levels[x][y - 1].equals("4")) {
				t = Engine.door_t_u;
			}

			if (x < Engine.levels.length - 1 && Engine.levels[x + 1][y].equals("2")) {
				// right
				r = Engine.door_r;
			} else if (x < Engine.levels.length - 1 && Engine.levels[x + 1][y].equals("3")) {
				r = Engine.door_r_u;
			} else if (x < Engine.levels.length - 1 && Engine.levels[x + 1][y].equals("4")) {
				r = Engine.door_r_u;
			}

			if (y < Engine.levels.length && Engine.levels[x][y + 1].equals("2")) {
				// down
				b = Engine.door_b;
			} else if (y < Engine.levels.length && Engine.levels[x][y + 1].equals("3")) {
				b = Engine.door_b_u;
			} else if (y < Engine.levels.length && Engine.levels[x][y + 1].equals("4")) {
				b = Engine.door_b_u;
			}

			if (inBounds(Engine.p.getRoomX() - 1, Engine.p.getRoomY())
					&& !(Engine.levels[Engine.p.getRoomX() - 1][Engine.p.getRoomY()].equals("1"))) {
				objects.add(new Doors(0, Engine.height / 2 - (Engine.door_l.getHeight() / 2), l));
			}
			// right
			if (inBounds(Engine.p.getRoomX() + 1, Engine.p.getRoomY())
					&& !(Engine.levels[Engine.p.getRoomX() + 1][Engine.p.getRoomY()].equals("1"))) {
				objects.add(new Doors(Engine.width - Engine.door_r.getWidth(),
						Engine.height / 2 - (Engine.door_r.getHeight() / 2), r));
			}
			// top
			if (inBounds(Engine.p.getRoomX(), Engine.p.getRoomY() - 1)
					&& !(Engine.levels[Engine.p.getRoomX()][Engine.p.getRoomY() - 1].equals("1"))) {
				objects.add(new Doors(Engine.width / 2 - (Engine.door_t.getWidth() / 2), 0, t));
			}
			// bot
			if (inBounds(Engine.p.getRoomX(), Engine.p.getRoomY() + 1)
					&& !(Engine.levels[Engine.p.getRoomX()][Engine.p.getRoomY() + 1].equals("1"))) {
				objects.add(new Doors(Engine.width / 2 - (Engine.door_b.getWidth() / 2),
						Engine.width - Engine.door_b.getWidth(), b));
			}
			num = 0;
		}

		for (int i = 0; i < objects.size(); i++) {
			if (!(objects.get(i).isAlive)) {
				objects.remove(i);
			} else {
				objects.get(i).update(gc);
			}
		}
		for (int i = 0; i < upgrades.size(); i++) {
			if (!(upgrades.get(i).isAlive)) {
				upgrades.remove(i);
			} else {
				upgrades.get(i).update(gc);
			}
		}
	}

}

class bossRoom extends Rooms {
	bossRoom(int x, int y, int width, int height) {
		super(x, y, width, height);
		enemy.add(new Boss1(300, 300, Engine.bossB));
	}

	public void draw(Graphics g) {
		g.setColor(c);
		g.fillRect(75, 75, Engine.width - 150, Engine.height - 150);
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).draw(g);
		}
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(g);
		}
	}

	public void update(GameContainer gc) {
		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).getCurH() <= 0) {
				objects.add(new ladder(enemy.get(i).getX(), enemy.get(i).getY(), Engine.rock));
				enemy.remove(i);
			} else {
				enemy.get(i).update(gc);
			}
		}
		for (int i = 0; i < objects.size(); i++) {
			if (!(objects.get(i).isAlive)) {
				objects.remove(i);
			} else {
				objects.get(i).update(gc);
			}
		}

	}

}

class blankRoom extends Rooms {
	blankRoom(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void draw(Graphics g) {
	}

	public void update(GameContainer gc) {
	}
}