package animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.Animation;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;

public class AnimProjectileMachineGun extends AnimProjectile
{	
	public AnimProjectileMachineGun(Point origin, Unit actor, GameObject target, boolean hit) {
		super(origin, actor, target, WEAPON_MG_ANIMATION_SPEED, hit);
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(getColor());
		g.fillOval(x, y, 5, 5);
		
		g.setLineWidth(2);
		g.setColor(Color.black);
		g.drawOval(x, y, 5, 5);
	}
}
