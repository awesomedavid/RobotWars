package item.weapon.ranged;

import actions.attack.AttackLaser;
import actions.attack.AttackSniper;
import animations.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;

public class WeaponSniper extends WeaponRanged
{
	public WeaponSniper(Unit owner)
	{
		super(owner);
		cost = WEAPON_SNIPER_COST;
		range = WEAPON_SNIPER_RANGE;
		cooldown = WEAPON_SNIPER_COOLDOWN;
		maxAmmo = WEAPON_SNIPER_AMMO;
		heat = WEAPON_SNIPER_HEAT;
		reloadTime = WEAPON_SNIPER_RELOAD;
		weight = WEAPON_PRIMARY_WEIGHT;
		curAmmo = maxAmmo;
		name = "Sniper";
		image = Images.primarySniperRifle; 

	}

	public void equip() {
		
	}
	
	// Fire at the specified target
	public void use(GameObject target) 
	{
		super.use(target);
		
		if(canShoot(target))
		{
			action(new AttackSniper(getOwner(), target, getAccuracyPenalty(target)));
		}
	}
	
	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		super.use(p);
		GameObject target = getObjectFromCell(p);
		
		if(target != null && canShoot(target))
		{
			action(new AttackSniper(getOwner(), target, getAccuracyPenalty(target)));
		}
	}


}
