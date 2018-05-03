package animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.Animation;
import core.Utility;
import objects.GameObject;
import objects.Point;
import objects.Team;
import objects.Unit;
import values.CoreValues;

public abstract class AnimBeam extends AnimTargeted {
	protected Unit actor;
	protected GameObject target;
	protected Team team;
	protected int width;
	
	int aXOff = CoreValues.CELL_SIZE/2;
	int aYOff = CoreValues.CELL_SIZE/2;
	int tXOff = CoreValues.CELL_SIZE/2;
	int tYOff = CoreValues.CELL_SIZE/2;
	
	final int SCATTER_NEAR = CoreValues.CELL_SIZE/6;
	final int SCATTER_FAR = CoreValues.CELL_SIZE;

	public AnimBeam(Point origin, Unit actor, GameObject target, int width, int duration, boolean hit) {
		super(origin, actor, target, duration, hit);
		
		this.actor = actor;
		this.target = target;
		team = actor.getTeam();
		this.width = width;
		
		// Always offset the attacker by a little for variety
		aXOff += randomSign(Utility.random(SCATTER_NEAR));
		aYOff += randomSign(Utility.random(SCATTER_NEAR));
		
		if(isHit())
		{
			tXOff += randomSign(Utility.random(SCATTER_NEAR));
			tYOff += randomSign(Utility.random(SCATTER_NEAR));
		}
		else
		{
			tXOff += randomSign(Utility.random(SCATTER_FAR/2, SCATTER_FAR));
			tYOff += randomSign(Utility.random(SCATTER_FAR/2, SCATTER_FAR));
		}
	}
	
	public int randomSign(int value)
	{
		if(Math.random() < .5)
		{
			return -value;
		}
		else
		{
			return value;
		}
	
	}

	abstract int getWidth();


	public void render(Graphics g)
	{
		if (ticks > duration)
			return;
		
		g.setLineWidth(getWidth());
		g.setColor(getColor());
		g.drawLine(actor.getXPixel()+aXOff, actor.getYPixel()+aYOff, target.getXPixel()+tXOff, target.getYPixel() + tYOff);
		g.setLineWidth(1);

	}

}
