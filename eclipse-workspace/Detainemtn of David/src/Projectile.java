import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Projectile extends GameObject {
	protected float w, h, damage;
	double xSpeed, ySpeed;
	public double range;
	public double rate;
	public boolean isAlive;

	Projectile(float x, float y, float w, float h, double xSpeed, double ySpeed, float damage, Image im) {
		super(x, y, im);
		isAlive = true;
		this.w = w;
		this.h = h;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.damage = damage;
	}

	abstract void update(GameContainer gc);

	abstract void draw(Graphics g);

	public double getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	public Image cImage(Image i) {
		if(Engine.p.direction.equals("down")) {
			i.setRotation(180);
		}else if(Engine.p.direction.equals("right")) {
			i.setRotation(90);
		}else if(Engine.p.direction.equals("left")){
			i.setRotation(-90);
		}else {
			i.setRotation(0);
		}
		return i;
	}
}
class PlayerProjectile extends Projectile {
	PlayerProjectile(float x, float y, float w, float h, double xSpeed, double ySpeed, float damage, Image im) {
		super(x, y, w, h, xSpeed, ySpeed, damage, im);

	}

	public void draw(Graphics g) {
		g.drawImage(im, x, y);

	}

	public void update(GameContainer gc) {

		x += xSpeed;
		y += ySpeed;
		range = range - rate;
	}

}
class BasicPlayerProjectile extends PlayerProjectile {
private int num = 1;
	BasicPlayerProjectile(float x, float y, float w, float h, double xSpeed, double ySpeed, float damage, Image im) {
		super(x, y, w, h, xSpeed, ySpeed, damage, im);
		imW = 20;
		imH = 20;
		range = 255;
		rate = .5;
	}

	public void draw(Graphics g) {
		if(num == 1) {
			im = cImage(im);
			num = 0;
		}
		g.drawImage(im, x, y);
	}

	public void update(GameContainer gc) {

		x += xSpeed;
		y += ySpeed;
		range = range - rate;
	}

}

class ExplosProj extends PlayerProjectile {
	public Image fireball;
	ExplosProj(float x, float y, float w, float h, double xSpeed, double ySpeed, float damage, Image im) {
		super(x, y, w, h, xSpeed, ySpeed, damage, im);
		imW = 20;
		imH = 20;
		range = 255;
		rate = .5;
	}

	public void draw(Graphics g) {
		g.drawImage(im.getScaledCopy(20,20), x, y);
	}

	public void update(GameContainer gc) {
		x += xSpeed;
		y += ySpeed;
		range = range - rate;
	}
}
class ExplosProjM extends PlayerProjectile {
	public Image fireball;
	ExplosProjM(float x, float y, float w, float h, double xSpeed, double ySpeed, float damage, Image im) {
		super(x, y, w, h, xSpeed, ySpeed, damage, im);
		imW = 15;
		imH = 15;
		range = 255;
		rate = .5;
	}

	public void draw(Graphics g) {
		g.drawImage(im.getScaledCopy(15,15), x, y);
	}

	public void update(GameContainer gc) {
		x += xSpeed;
		y += ySpeed;
		range = range - rate;
	}
}

class EnemyProjectile extends Projectile {
	EnemyProjectile(float x, float y, float w, float h, double xSpeed, double ySpeed, float damage, Image im) {
		super(x, y, w, h, xSpeed, ySpeed, damage, im);
	}

	public void draw(Graphics g) {
		g.drawImage(im, x, y);
	}

	public void update(GameContainer gc) {

		x += xSpeed;
		y += ySpeed;
	}

}
class BasicEnemyProjectile extends EnemyProjectile {
	BasicEnemyProjectile(float x, float y, float w, float h, double xSpeed, double ySpeed, float damage, Image im) {
		super(x, y, w, h, xSpeed, ySpeed, damage, im);
		imW = 10;
		imH = 10;
		range = 255;
		rate = .4;

	}

	public void draw(Graphics g) {
		g.drawImage(im.getScaledCopy(10,10), x, y);
	}

	public void update(GameContainer gc) {
		range = range - rate;
		x += xSpeed;
		y += ySpeed;
	}

}
