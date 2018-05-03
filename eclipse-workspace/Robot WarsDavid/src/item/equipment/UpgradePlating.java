package item.equipment;

import objects.Robot;

public class UpgradePlating extends Upgrade 
{
	
	public UpgradePlating(Robot owner)
	{
		super(owner);
		cost = UPGRADE_PLATING_COST;
	}
	
	public void equip()
	{
		getOwner().increaseMaxPlating(UPGRADE_PLATING_AMOUNT);
	}

}
