package values;

import core.DamageType;

public interface WeaponValues 
{
	
	// General
	final static int WEAPON_PRIMARY_WEIGHT = 2;
	final static int WEAPON_SECONDARY_WEIGHT = 1;

	// Sniper
	final static int WEAPON_SNIPER_COST = 5;	
	final static int WEAPON_SNIPER_RANGE = 30;	
	final static int WEAPON_SNIPER_AIM_TIME = 50;
	final static float WEAPON_SNIPER_DAMAGE = 12;
	final static int WEAPON_SNIPER_COOLDOWN = 200;
	final static int WEAPON_SNIPER_RELOAD = 400;

	final static int WEAPON_SNIPER_ANIMATION_SPEED = 30;
	final static int WEAPON_SNIPER_AMMO = 3;
	final static int WEAPON_SNIPER_HEAT = 30;

	final static DamageType WEAPON_SNIPER_TYPE = DamageType.PIERCE;
	
	// Laser
	final static int WEAPON_LASER_COST = 5;	
	final static int WEAPON_LASER_RANGE = 10;	
	final static int WEAPON_LASER_AIM_TIME = 1;
	final static float WEAPON_LASER_DAMAGE = 12;
	final static int WEAPON_LASER_COOLDOWN = 100;
	final static int WEAPON_LASER_RELOAD = 250;
	final static int WEAPON_LASER_ANIMATION_DURATION = 25;
	final static int WEAPON_LASER_ANIMATION_WIDTH = 5;
	final static int WEAPON_LASER_AMMO = 10;
	final static int WEAPON_LASER_HEAT = 60;

	final static DamageType WEAPON_LASER_TYPE = DamageType.ENERGY;
	
	// Machine Gun
	final static int WEAPON_MG_COST = 5;	
	final static int WEAPON_MG_RANGE = 10;	
	final static int WEAPON_MG_AIM_TIME = 5;
	final static float WEAPON_MG_DAMAGE = 2;
	final static int WEAPON_MG_COOLDOWN = 10;
	final static int WEAPON_MG_RELOAD = 1000;
	final static int WEAPON_MG_AMMO = 100;
	final static int WEAPON_MG_ANIMATION_SPEED = 12;
	final static int WEAPON_MG_HEAT = 2;

	final static DamageType WEAPON_MG_TYPE = DamageType.NORMAL;
	
	// Drill
	
	final static int WEAPON_DRILL_COST = 5;	
	final static int WEAPON_DRILL_RANGE = 1;	
	final static int WEAPON_DRILL_AIM_TIME = 1;
	final static float WEAPON_DRILL_DAMAGE = 1;
	final static int WEAPON_DRILL_COOLDOWN = 5;	
	final static int WEAPON_DRILL_ANIMATION_DURATION = 5;
	final static DamageType WEAPON_DRILL_TYPE = DamageType.PIERCE;
	final static int WEAPON_DRILL_HEAT = 2;

	
	// Chainsaw
	
	final static int WEAPON_CHAINSAW_COST = 5;	
	final static int WEAPON_CHAINSAW_RANGE = 1;	
	final static int WEAPON_CHAINSAW_AIM_TIME = 1;
	final static float WEAPON_CHAINSAW_DAMAGE = 1;
	final static int WEAPON_CHAINSAW_COOLDOWN = 1;	
	final static int WEAPON_CHAINSAW_ANIMATION_DURATION = 5;
	final static DamageType WEAPON_CHAINSAW_TYPE = DamageType.NORMAL;
	final static int WEAPON_CHAINSAW_HEAT = 2;	



}
