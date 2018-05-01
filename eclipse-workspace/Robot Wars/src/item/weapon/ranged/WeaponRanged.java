package item.weapon.ranged;

import item.weapon.ShotCalculator;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Robot;
import objects.Unit;

public abstract class WeaponRanged extends Weapon 
{
	public WeaponRanged(Unit owner)
	{
		super(owner);
		targetObject = true;
	}
	
	public float getAccuracyPenalty(GameObject target)
	{
		return ShotCalculator.calcShot(getOwner(), target).getPenalty();
	}
	
}
