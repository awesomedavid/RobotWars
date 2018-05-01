import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Boss1 extends Enemies {

	float proW, proH, proDam, shotTimer, shotDelay;
	double proxSpeed, proySpeed;
	private double angle = 0;

	Boss1(float x, float y, Image im) {
		super(x, y, im);
		maxH = 150;
		curH = maxH;
	}

	public void draw(Graphics g) {

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
		g.drawImage(im, x, y);
		g.setColor(new Color(100,0,0));
		g.fillRect(x-10,y-10,(float) (maxH/1.5),10);
		g.setColor(new Color(255,0,0));
		g.fillRect(x-10,y-10,(float) (curH/1.5),10);
	}

	public void update(GameContainer gc) {
		angle = Math.toDegrees(Math.atan2(Engine.p.getY() - y, Engine.p.getX() - x));
		for (int i = 0; i < Engine.p.projectiles.size(); i++) {
			if (collision(Engine.p.projectiles.get(i).getX(), Engine.p.projectiles.get(i).getX() + + Engine.p.projectiles.get(i).getImW(),
					Engine.p.projectiles.get(i).getY(), Engine.p.projectiles.get(i).getY() + + Engine.p.projectiles.get(i).getImH())) {
				curH = curH - 50;
				Engine.p.projectiles.remove(i);
			}
		}
	}
}
