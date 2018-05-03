package item.weapon.melee;

import actions.attack.AttackMachineGun;
import actions.attack.AttackLaser;
import animations.AnimBeamBurstLaser;
import core.Game;
import files.Images;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;

public class WeaponChainsaw extends Weapon 
{
	public WeaponChainsaw(Unit owner)
	{
		super(owner);
		cost = WEAPON_CHAINSAW_COST;
		range = WEAPON_CHAINSAW_RANGE;
		cooldown = WEAPON_CHAINSAW_COOLDOWN;
		heat = WEAPON_CHAINSAW_HEAT;
		weight = WEAPON_PRIMARY_WEIGHT;
		name = "Chainsaw";
		image = Images.primaryChainsaw;
		needsAmmo = false;

	}

	public void equip() 
	{
		getOwner().enableChop();
	}
	
	public void use(GameObject target) 
	{
		super.use(target);
		
		//action(new AttackMachineGun(getOwner(), target));
	}
	
	
	public void use(Point p) 
	{
	
	}
	
	public void use() 
	{
	
	}
	
	

}
