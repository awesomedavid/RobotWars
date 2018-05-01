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
import world.cells.terrain.Floor;

public class BuildFloor extends Build 
{	
	public BuildFloor(Unit actor, Class<? extends Floor> type)
	{
		super(actor, BUILD_FLOOR_TIME, type);
	}
		
}
