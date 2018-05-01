package item.weapon;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

import actions.Action;
import actions.Reload;
import animations.Animation;
import core.Game;
import core.Utility;
import effects.Damage;
import item.equipment.Upgrade;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;
import values.WeaponValues;
import world.World;
import world.cells.Cell;

public abstract class Weapon extends Upgrade  implements WeaponValues
{
	protected int range;
	protected int cooldown;
	protected int cooldownLeft;
	protected int speed;
	protected int heat;
	protected boolean needsAmmo;
	protected boolean targetObject;
	protected boolean targetPoint;
	protected boolean targetAuto;
	protected int curAmmo;
	protected int maxAmmo;
	protected int reloadTime = -1;
	protected String name;
	protected Image image;
	
	public Weapon(Unit owner)
	{
		super(owner);
		name = this.getClass().getSimpleName();
		needsAmmo = true;
	}

	
	public float getRange() {
		return range;
	}
	
	public int getReloadTime()
	{
		 return reloadTime; 			 
	}
	
	public boolean usesAmmo()
	{
		return needsAmmo;
	}
	
	public boolean targetPoint()
	{
		return targetPoint;
	}
	
	public boolean targetAuto()
	{
		return targetAuto;
	}
	
	public boolean targetObject()
	{
		return targetObject;
	}
	
	public boolean hasAmmo()
	{
		return curAmmo > 0 || !needsAmmo;
	}
	
	public boolean needsReload()
	{
		return curAmmo == 0 && needsAmmo;
	}
	
	public int getHeatCost()
	{
		return heat;
	}
	
	public int getCurAmmo()
	{
		return curAmmo;
	}
	
	public int getMaxAmmo()
	{
		return maxAmmo;
	}
	
	public void reload()
	{
		if(getOwner().hasAction() && Reload.class.isInstance(getOwner().getAction()) )
		{
			curAmmo = maxAmmo;
		}
	}
	
	public void setRange(int value)
	{
		range = value;
	}

	public void update() {
		if (cooldownLeft > 0 && getOwner().isAlive()) {
			cooldownLeft--;
		}
	}

	public boolean canShoot(GameObject a) {
		return  getOwner() != null && a != null &&				// Check null
				getOwner().isAlive() && a.isAlive() &&			// Check if the owner and target are alive
				!isOnCooldown()  && 							// Check that the shot isn't on cooldown
				 ((needsAmmo && hasAmmo()) || !needsAmmo) &&    // Check that the weapon needs ammo
				 getOwner().inRange(a, this)  &&				// Check that the target is in range
				 !getOwner().isOverheated()	 && 				// Check that the robot is not overheated
				 !noClearShot(a);					    		// Check that the target isn't fully blocked by cover
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public boolean noClearShot(GameObject u)
	{
		return ShotCalculator.calcShot(this.getOwner(), u).getPenalty() >= 1; 
	}
	
	public boolean isOnCooldown()
	{
		return cooldownLeft > 0;
	}
	

	// How many frames does it take to reach the target at my speed?
	public int getDelay(Unit a) {
		return (int) (Utility.distance(getOwner(), a) / speed);
	}

	public void action(Action a)
	{
		getOwner().setAction(a);
		cooldownLeft = cooldown;
	}
	
	public String toString()
	{
		return name;
	}
	
	
	
	public void use(GameObject target)
	{
		if(targetObject() && canShoot(target))
		{
			if(usesAmmo())
			{
				curAmmo--;
			}		

			getOwner().addHeat(heat);
		}
	}
	
	public void use()
	{
		if(targetAuto())
		{
			if(usesAmmo())
			{
				curAmmo--;
			}	
			getOwner().addHeat(heat);
		}
	}

	public void use(Point p)
	{
		if(targetPoint())
		{
			if(usesAmmo())
			{
				curAmmo--;
			}	
			getOwner().addHeat(heat);
		}
		else if(targetObject && getObjectFromCell(p) != null && canShoot(getObjectFromCell(p)))
		{
			if(usesAmmo())
			{
				curAmmo--;
			}	
			getOwner().addHeat(heat);
		}
	}

	
	public GameObject getObjectFromCell(Point p)
	{
		if(World.getCell(p) != null)
		{
			if(World.getCell(p).hasUnit())
			{
				return World.getCell(p).getUnit();
			}
			else if(World.getCell(p).hasFeature())
			{
				return World.getCell(p).getFeature();
			}			
		}
		return null;
	}

//	public boolean use(Unit a) {
//		if (canShoot(a)) {
//			delay = getDelay(a);
//
//			// Basic Damage
//			a.addEffect(new Damage(a, owner, delay, damage));
//
//			owner.actionComplete();
//			shotTimer = cooldown;
//			animation(a, getDelay(a));
//
//			return true;
//		} else {
//			return false;
//		}
//
//	}
}
