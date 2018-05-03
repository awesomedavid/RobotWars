package animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import objects.GameObject;
import objects.Point;
import objects.Unit;

public abstract class AnimTargeted extends Animation{

	private Unit actor;
	protected Point target;
	protected GameObject targetUnit;
	private boolean hit;
	
	public AnimTargeted(Point origin, Unit actor, GameObject target, int duration, boolean hit) {
		super(origin.getX(), origin.getY(), duration);
		this.actor = actor;
		this.targetUnit = target;
		this.target = target.getPointPixel();
		this.hit = hit;

	}

	public Unit getActor()
	{
		return actor;
	}
	
	public Color getColor()
	{
		return actor.getTeam().getColor();
	}
	
	
	public Point getTarget()
	{
		return target;
	}
	
	public boolean isHit()
	{
		return hit;
	}

}
