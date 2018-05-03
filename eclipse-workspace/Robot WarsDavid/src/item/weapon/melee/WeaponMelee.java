package item.weapon.melee;

import item.weapon.Weapon;
import objects.Robot;
import objects.Unit;

public abstract class WeaponMelee extends Weapon 
{
	public WeaponMelee(Unit owner)
	{
		super(owner);
		targetObject = true;
	}
	
}
