import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Upgrades extends Objects {
	Upgrades(float x, float y, Image im) {
		super(x, y, im);
	}

	abstract void update(GameContainer gc);

	abstract void draw(Graphics g);
}

class ExplosProjU extends Upgrades {
	public ExplosProjU(int x, int y, Image im) {
		super(x, y, im);
	}

	void update(GameContainer gc) {
		if (collision(Engine.p.getX(), Engine.p.getX() + 50, Engine.p.getY(), Engine.p.getY() + 50)) {
			Engine.p.upgrades.add(this);
			isAlive = false;
		}
	}

	void draw(Graphics g) {
		g.drawImage(im, x, y);
	}
}
