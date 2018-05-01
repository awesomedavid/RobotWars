package actions;

import objects.Unit;
import values.ActionValues;
import values.WeaponValues;

public abstract class Action implements ActionValues, WeaponValues
{	
	protected Unit actor;
	protected int duration;
	protected int time;
	private boolean cancelled;
	
	protected Action(Unit actor, int duration)
	{
		this.actor = actor;
		this.duration = duration;
		this.time = 0;
	}
	
	public boolean isComplete()
	{
		return time == duration ;
	}
	
	public boolean isCancelled()
	{
		return cancelled;
	}
		
	public void update()
	{
		time++;

	//	System.out.println(this + ": " + time + "/" + duration);	

		if(time == duration && !isCancelled())
		{
			complete();
		}
		
		if(!actor.isAlive())
		{
			cancel();
		}
				
		// add code to cancel actions from dead guys

	}
	
	public Unit getActor()
	{
		return actor;
	}
	
	public float getPercentComplete()
	{
		return (float) time / (float) duration;
	}
	
	public String toString()
	{
		return getClass().getSimpleName() + "";
	}
	
	public void cancel()
	{
		cancelled = true;
		time = duration;
	}
	
	protected abstract void complete();

}
