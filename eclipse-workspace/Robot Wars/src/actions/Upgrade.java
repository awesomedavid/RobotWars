package actions;

import item.Item;
import item.Metal;
import item.StoneBlock;
import item.WoodLog;
import item.equipment.Equipment;
import objects.Direction;
import objects.Robot;
import objects.Unit;
import world.World;
import world.cells.Cell;
import world.cells.feature.Stone;

public class Upgrade extends Action 
{
	int cost;
	Equipment gear;
	
	public Upgrade(Robot actor, Equipment e)
	{
		super(actor, UPGRADE_TIME_PER_METAL_COST * e.getCost());
		cost = e.getCost();
		
		// Pay the cost at the start
		actor.getTeam().loseMetal(cost);
	}
		
	protected void complete()
	{
		((Robot) actor).equip(gear);
	}
	
	public void cancel()
	{
		super.cancel();
		
		// Refund cost if the action is interrupted
		actor.getTeam().addMetal(cost);
		
	}
}
