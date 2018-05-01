import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class BasicE1 extends Enemies {

	float proW, proH, proDam, shotTimer, shotDelay;
	double proxSpeed, proySpeed;
	private double angle = 0;
	float rate2;

	BasicE1(float x, float y, Image im) {
		super(x, y, im);
		rate2 = (float)(Math.random());
		maxH = 100;
		curH = maxH;
		proW = 10;
		proH = 10;
		shotTimer = 0;
		shotDelay = 600;
	}

	public void draw(Graphics g) {
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
		g.drawImage(im, x, y);
	}

	public void update(GameContainer gc) {
		if (collision(Engine.p.getX(), Engine.p.getX() + Engine.wizard.getWidth(), Engine.p.getY(),
				Engine.p.getY() + Engine.wizard.getHeight())) {
			if (Engine.p.getShield() <= 0) {
				Engine.p.setCurH(Engine.p.getCurH() - 1);
			}
			if (angle < -45 && angle > -135) {
				Engine.p.setY(Engine.p.getY() - 100);
			} else if (angle > -45 && angle < 45) {
				Engine.p.setX(Engine.p.getX() + 100);
			} else if (angle > 45 && angle < 135) {
				Engine.p.setY(Engine.p.getY() + 100);
			} else {
				Engine.p.setX(Engine.p.getX() - 100);
			}
			Engine.p.setShield(50);

		}
		angle = Math.toDegrees(Math.atan2(Engine.p.getY() - y, Engine.p.getX() - x));
		speeds();
		if (shotTimer > 0) {
			shotTimer=shotTimer-rate2;;
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).getX() < 0 || projectiles.get(i).getX() > Engine.width
					|| projectiles.get(i).getY() < 0 || projectiles.get(i).getY() > Engine.height
					|| projectiles.get(i).getRange() <= 0) {
				projectiles.remove(i);
			} else {
				projectiles.get(i).update(gc);
			}

		}
		if (shotTimer <= 0) {
			projectiles.add(new BasicEnemyProjectile(x + im.getWidth() / 2, y + im.getHeight() / 2, proW, proH,
					proxSpeed, proySpeed, proDam, Engine.fireball));
			shotTimer = shotDelay;
		}
		for (int i = 0; i < Engine.p.projectiles.size(); i++) {
			if (collision(Engine.p.projectiles.get(i).getX(),
					Engine.p.projectiles.get(i).getX() + Engine.p.projectiles.get(i).getImW(),
					Engine.p.projectiles.get(i).getY(),
					Engine.p.projectiles.get(i).getY() + Engine.p.projectiles.get(i).getImH())) {
				if (Engine.p.projectiles.get(i) instanceof BasicPlayerProjectile) {
					curH -= 50;
					Engine.p.projectiles.remove(i);
				} else if (Engine.p.projectiles.get(i) instanceof ExplosProj) {
					curH -= 10;
					Engine.p.projectiles.remove(i);
					Engine.p.projectiles.add(new ExplosProjM(x, y, proW, proH, .35, 0, proDam, Engine.upgrade_ex));
					Engine.p.projectiles.add(new ExplosProjM(x, y, proW, proH, -.35, 0, proDam, Engine.upgrade_ex));
					Engine.p.projectiles.add(new ExplosProjM(x, y, proW, proH, 0, .35, proDam, Engine.upgrade_ex));
					Engine.p.projectiles.add(new ExplosProjM(x, y, proW, proH, 0, -.35, proDam, Engine.upgrade_ex));
					Engine.p.projectiles.add(new ExplosProjM(x, y, proW, proH, -.3, -.3, proDam, Engine.upgrade_ex));
					Engine.p.projectiles.add(new ExplosProjM(x, y, proW, proH, .3, -.3, proDam, Engine.upgrade_ex));
					Engine.p.projectiles.add(new ExplosProjM(x, y, proW, proH, .3, .3, proDam, Engine.upgrade_ex));
					Engine.p.projectiles.add(new ExplosProjM(x, y, proW, proH, -.3, .3, proDam, Engine.upgrade_ex));
				} else if (Engine.p.projectiles.get(i) instanceof ExplosProjM) {
					curH -= 1;
				}
			}
		}
		movement();
	}

	public void movement() {
		if (x != Engine.p.getX() || y != Engine.p.getY()) {
			x += (float) (Math.cos(Math.toRadians(angle)) / 8);
			y += (float) (Math.sin(Math.toRadians(angle)) / 8);
		}
	}

	public void speeds() {
		if (!(x == Engine.p.getX() || y == Engine.p.getY())) {
			proySpeed = Math.sin(Math.toRadians(angle)) / 4;
			proxSpeed = Math.cos(Math.toRadians(angle)) / 4;
		}
	}

	public void borders() {
		if (x < 0)
			x = 0;
		if (x + im.getWidth() > Engine.width)
			x = Engine.width - im.getWidth();
		if (y < 0)
			y = 0;
		if (y + im.getHeight() > Engine.height)
			y = Engine.height - im.getHeight();
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
}
