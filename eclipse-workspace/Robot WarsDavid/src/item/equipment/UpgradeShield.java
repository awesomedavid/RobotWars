package item.equipment;

import objects.Robot;

public class UpgradeShield extends Upgrade 
{
	
	public UpgradeShield(Robot owner)
	{
		super(owner);
		cost = UPGRADE_SHIELD_COST;
	}
	
	public void equip()
	{
		getOwner().increaseMaxShield(UPGRADE_SHIELD_AMOUNT);
		getOwner().increaseShieldRegen(UPGRADE_SHIELD_REGEN);
	}

}
