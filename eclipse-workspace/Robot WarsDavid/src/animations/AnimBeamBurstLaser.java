package animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.Animation;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;

public class AnimBeamBurstLaser extends AnimBeamBurst 
{	
	public AnimBeamBurstLaser(Point origin, Unit actor, GameObject target, boolean hit) {
		super(origin, actor, target, WEAPON_LASER_ANIMATION_WIDTH, WEAPON_LASER_ANIMATION_DURATION, hit);
	}
}
