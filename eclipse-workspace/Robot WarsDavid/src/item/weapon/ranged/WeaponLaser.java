package item.weapon.ranged;

import actions.attack.AttackLaser;
import actions.attack.AttackMachineGun;
import animations.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;

public class WeaponLaser extends WeaponRanged
{
	public WeaponLaser(Unit owner)
	{
		super(owner);
		cost = WEAPON_LASER_COST;
		range = WEAPON_LASER_RANGE;
		cooldown = WEAPON_LASER_COOLDOWN;
		maxAmmo = WEAPON_LASER_AMMO;
		heat = WEAPON_LASER_HEAT;
		reloadTime = WEAPON_LASER_RELOAD;
		weight = WEAPON_PRIMARY_WEIGHT;
		curAmmo = maxAmmo;
		name = "Laser";
		//image = Images.primaryDrill; 
		image = Images.primaryLaser; 
		
		

	
	}

	public void equip() {
		
	}
	
	// Fire at the specified target
	public void use(GameObject target) 
	{
		super.use(target);

		if(canShoot(target))
		{
			action(new AttackLaser(getOwner(), target, getAccuracyPenalty(target)));
		}
	}
	

	// Fire at the unit in the specific cell, if there is one
	public void use(Point p) 
	{
		super.use(p);
		GameObject target = getObjectFromCell(p);
		
		if(target != null && canShoot(target))
		{
			action(new AttackLaser(getOwner(), target, getAccuracyPenalty(target)));
		}
	}

}
