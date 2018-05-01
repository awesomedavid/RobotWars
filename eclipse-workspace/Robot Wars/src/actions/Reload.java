package actions;

import item.Item;
import item.Metal;
import item.StoneBlock;
import item.WoodLog;
import item.equipment.Equipment;
import item.weapon.Weapon;
import objects.Direction;
import objects.Robot;
import objects.Unit;
import world.World;
import world.cells.Cell;
import world.cells.feature.Stone;

public class Reload extends Action 
{
	Weapon weapon;
	
	public Reload(Unit actor, Weapon w)
	{
		super(actor, w.getReloadTime());
		weapon = w;
		
		if(!w.usesAmmo())
		{
			cancel();
		}
	}
		
	protected void complete()
	{
		weapon.reload();
	}
	
}
