package teams.starter.wander;

import org.newdawn.slick.SlickException;

import core.Utility;
import item.Item;
import item.StoneBlock;
import objects.Robot;
import objects.Unit;
import world.cells.feature.Stone;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class WanderRobot extends Robot 
{
	int steps = 0;
	int distance = 0;
	
	public WanderRobot(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
	}
	
	// Action method is called when a robot has no current action
	public void action()
	{		
		Unit target = getNearestEnemy();
		
		if(getPrimary().canShoot(target))
		{
			usePrimary(getNearestEnemy());
		}
		else if(getCell().hasItem(Item.class))
		{
			pickup();
		}
		else if(inZoneHostile() || inZoneNeutral())
		{
			
			if(getTimer() % 10 == 0)
			{
				turnLeft();
			}
		}
		else
		{
			if(steps > distance)
			{
				steps = 0;
				turnRandom();
			}	
			else if(!frontIsClear())
			{
				turnRandom();
				steps = 0;
				if(distance > 1)
				{
					distance--;
				}
			}
			else if(frontIsClear())
			{
				move();
				steps++;
				distance++;
			}
		}	
	}
	
	// This method is called when the robot dies
	public void onDeath()
	{
		
	}
	
	// A supporting method for the basic robot
	public void turnRandom()
	{	
		int r = Utility.random(4);
		
		switch(r)
		{
		case 0:
			turnLeft();
			break;
		case 1:
			turnRight();
			break;
		case 2:
			turnAround();
			break;
		}
	}
}