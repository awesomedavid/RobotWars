package actions.attack;


import actions.Action;
import animations.Animation;
import animations.text.AnimFloatTextAttack;
import core.Game;
import core.Utility;
import effects.Effect;
import item.StoneBlock;
import item.WoodLog;
import objects.Direction;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;
import world.World;
import world.cells.Cell;
import world.cells.feature.Stone;

public abstract class Attack extends Action 
{
	protected float accuracyPenalty;
	protected Unit actor;
	protected GameObject target;
	protected float damage;
	protected boolean hit;
	protected int speed;
	
	public Attack(Unit actor, GameObject target, int aimTime, int shotSpeed, float accuracyPenalty)
	{
		super(actor, aimTime);
		this.actor = actor;
		this.target = target;
		this.accuracyPenalty = accuracyPenalty;
		this.speed = shotSpeed;
		actor.turnTo(target);

	}

	protected void complete()
	{
		actor.turnTo(target);
		
		makeAttackRoll();
		
		if(!hit)
		{
			Game.addAnimation(new AnimFloatTextAttack("Miss!", target, calculateDelay()));
		}
	}
	
	public GameObject getTarget()
	{
		return target;
	}
	
	public int calculateDelay()
	{
		return calculateDelay(speed);
	}
	
	public int calculateDelay(float speed)
	{
		return calculateDelay(actor.getPointPixel(), target.getPointPixel(), speed);
	}
	
	public int calculateDelay(Point p, Point t, float speed)
	{
		if(speed <= 0)
		{
			return 0;
		}
		else
		{
			return Math.round((Utility.distance(actor,  target)* ((float) CoreValues.CELL_SIZE) / speed)); 
		}
	}
	
	public void makeAttackRoll()
	{
		if(Math.random() - accuracyPenalty > 0)
		{
			hit = true;
		}
		else
		{
			hit = false;
			damage = 0;
		}
	}
	public boolean hit()
	{
		return hit;
	}
	
	public void update()
	{
		super.update();
		actor.turnTo(target);
				
		if(!getActor().isAlive() || !target.isAlive())
		{
			cancel();
		}
	}
	
	
	public void effect(Effect e)
	{
		target.addEffect(e);
	}
	public void animation(Animation a)
	{
		Game.addAnimation(a);
	}
	
	

}
