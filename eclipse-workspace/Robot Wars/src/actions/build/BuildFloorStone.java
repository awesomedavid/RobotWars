package actions.build;

import java.lang.reflect.InvocationTargetException;

import item.Item;
import item.StoneBlock;
import objects.Direction;
import objects.Unit;
import world.World;
import world.cells.Cell;
import world.cells.feature.Feature;
import world.cells.feature.Stone;
import world.cells.feature.Wall;
import world.cells.feature.WallStone;
import world.cells.terrain.FloorStone;

public class BuildFloorStone extends BuildFloor
{	
	public BuildFloorStone(Unit actor)
	{
		super(actor, FloorStone.class);
	}
		
}
