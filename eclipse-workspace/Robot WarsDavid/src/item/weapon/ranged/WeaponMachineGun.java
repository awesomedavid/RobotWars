package item.weapon.ranged;

import actions.attack.AttackMachineGun;
import actions.attack.AttackSniper;
import actions.attack.AttackLaser;
import animations.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;

public class WeaponMachineGun extends WeaponRanged 
{
	public WeaponMachineGun(Unit owner)
	{
		super(owner);
		cost = WEAPON_MG_COST;
		range = WEAPON_MG_RANGE;
		cooldown = WEAPON_MG_COOLDOWN;
		maxAmmo = WEAPON_MG_AMMO;
		heat = WEAPON_MG_HEAT;
		reloadTime = WEAPON_MG_RELOAD;
		weight = WEAPON_PRIMARY_WEIGHT;
		curAmmo = maxAmmo;
		name = "MG";
		image = Images.primaryMachineGun; 
	}


	public void equip() {
		
	}
	
	// Fire at the specified target
	public void use(GameObject target) 
	{
		super.use(target);

		if(canShoot(target))
		{
			action(new AttackMachineGun(getOwner(), target, getAccuracyPenalty(target)));
		}
	}

	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		super.use(p);
		GameObject target = getObjectFromCell(p);
		
		if(target != null && canShoot(target))
		{
			action(new AttackMachineGun(getOwner(), target, getAccuracyPenalty(target)));
		}
	}
}
