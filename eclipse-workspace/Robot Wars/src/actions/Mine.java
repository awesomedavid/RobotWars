package actions;

import item.StoneBlock;
import item.WoodLog;
import objects.Direction;
import objects.Unit;
import world.World;
import world.cells.Cell;
import world.cells.feature.Stone;

public class Mine extends Harvest 
{
	public Mine(Unit actor, Stone stone)
	{
		super(actor, stone);
		this.rate = MINING_RATE_BASE;
		this.ratePerSkill = MINING_RATE_PER_SKILL;
		this.skill = actor.getMiningSkill();
		duration = target.getHarvestTime(rate, ratePerSkill, skill);
	}
	
	protected void complete()
	{
		World.addItemToCell(new StoneBlock(), target.getX(), target.getY());
	}
	

}
