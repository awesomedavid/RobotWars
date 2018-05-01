package objects;

import java.util.ArrayList;
import java.util.List;

import item.Item;
import item.Resource;
import item.equipment.Equipment;
import item.equipment.Upgrade;
import item.weapon.Weapon;

public class Inventory 
{
	Unit owner;
	Weapon primary;
	Weapon secondary;
	List<Upgrade> upgrades;
	private int weightCapacity;
	private int weightCarried;
	
	
	Inventory(Unit owner)
	{
		this.owner = owner;
		upgrades = new ArrayList<Upgrade>();
	}
	
	public int getWeightCapacity()
	{
		return weightCapacity;
	}

	public int getWeightCarried()
	{
		return weightCarried;
	}
	
	public int getWeightCapacityLeft()
	{
		return weightCapacity - weightCarried;
	}
	
	public boolean canCarry(Equipment e)
	{
		return getWeightCapacityLeft() > e.getWeight();
	}
	
	void add(Equipment e)
	{
		if(canCarry(e))
		{
			// Weapon
			if(e instanceof Weapon)
			{
				if(primary == null)
				{
					primary = (Weapon) e;
					primary.equip();
				}
				else if(secondary == null)
				{
					secondary = (Weapon) e;
					secondary.equip();
				}
			}
			
			// Upgrades
			else
			{
				upgrades.add((Upgrade) e);
				e.equip();
			}
		}
	}
	
	public void update()
	{
		if(hasPrimary())
		{
			primary.update();
		}
		if(hasSecondary())
		{
			secondary.update();
		}

	}
	
	public boolean isEmpty()
	{
		return primary == null && secondary == null && upgrades.isEmpty();
	}
	
	public boolean hasPrimary()
	{
		return primary != null;
	}
	
	public boolean hasSecondary()
	{
		return secondary != null;
	}
	
	public Weapon getPrimary()
	{
		return primary;
	}
	
	public Weapon getSecondary()
	{
		return secondary;
	}
	
	public void increaseMaxWeight(int amount)
	{
		weightCapacity += amount;
	}
	
	
	
	
}
