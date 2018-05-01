package animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.Animation;
import core.Utility;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;


public class AnimProjectile extends AnimTargeted {
	
	protected int speed;
	
	public AnimProjectile(Point origin, Unit actor, GameObject target, int speed, boolean hit) {
		super(origin, actor, target, Utility.distance(actor, target) * CoreValues.CELL_SIZE / speed, hit);
		this.speed = speed;

	}
	
	public void update() {
		super.update();
		
		target = targetUnit.getPointPixel();
		
		// Calculate Current X Position
		float xDiff = target.getX() - xOrigin;
		x = xOrigin + CELL_SIZE/2 + xDiff * (float) ticks / (float) duration;

		float yDiff =  target.getY() - yOrigin;
		y = yOrigin + CELL_SIZE/2 + yDiff * (float) ticks / (float) duration;

	}

	public void render(Graphics g) {
	
	
		g.setColor(getColor());
		g.fillOval(x, y, 10, 10);
		
		
	}

}
