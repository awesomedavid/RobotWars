package item.equipment;

import item.Item;
import objects.Unit;

public abstract class Equipment extends Item 
{
	protected int weight;
	
	private Unit owner;
	Equipment(Unit owner) {
		this.owner = owner;

	}

	protected int cost;
	
	public int getCost()
	{
		return cost;
	}
	
	public Unit getOwner()
	{
		return owner;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	abstract public void equip();
	
}
