import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public abstract class Enemies extends GameObject {
	boolean isAlive;
	protected ArrayList<EnemyProjectile> projectiles = new ArrayList<>();
	float curH, maxH;

	Enemies(float x, float y, Image im) {
		super(x, y, im);
		isAlive = true;
	}

	abstract void update(GameContainer gc);

	abstract void draw(Graphics g);

	public float getCurH() {
		return curH;
	}

	public void setCurH(float curH) {
		this.curH = curH;
	}

	public float getMaxH() {
		return maxH;
	}

	public void setMaxH(float maxH) {
		this.maxH = maxH;
	}

}