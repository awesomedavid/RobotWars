package item.weapon.melee;

import actions.attack.AttackMachineGun;
import actions.attack.AttackDrill;
import actions.attack.AttackLaser;
import animations.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;

public class WeaponDrill extends Weapon 
{
	public WeaponDrill(Unit owner)
	{
		super(owner);
		cost = WEAPON_DRILL_COST;
		range = WEAPON_DRILL_RANGE;
		cooldown = WEAPON_DRILL_COOLDOWN;
		heat = WEAPON_DRILL_HEAT;
		weight = WEAPON_PRIMARY_WEIGHT;
		name = "Drill";
		image = Images.primaryDrill; 
		needsAmmo = false;
		targetObject = true;
	}

	public void equip() 
	{  
		getOwner().enableMine();
	}
	
	public void use(GameObject target) 
	{
		super.use(target);

		if(canShoot(target))
		{
			action(new AttackDrill(getOwner(), target));
		}
	}
	
	public void use(Point p) 
	{
	
	}
	
	public void use() 
	{
	
	}
	
	

}
