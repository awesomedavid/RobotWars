package teams.starter.approach;

import org.newdawn.slick.SlickException;

import core.Utility;
import item.Item;
import item.StoneBlock;
import objects.Robot;
import objects.Unit;
import world.cells.feature.Stone;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class ApproachRobot extends Robot 
{
	int abandonMove;
	
	public ApproachRobot(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
	}
	
	// Action method is called when a robot has no current action
	public void action()
	{		
		Unit target = getNearestEnemy();
		abandonMove = 0;
		
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
		else if(target.getX() > getX())
		{
			turnEast();
			attemptMove();
		}	
		else if(target.getX() < getX())
		{
			turnWest();
			attemptMove();
		}	
		else if(target.getY() > getY())
		{
			turnSouth();
			attemptMove();
		}	
		else if(target.getY() < getY())
		{
			turnNorth();
			attemptMove();
		}	
		else
		{
			turnRandom();
			attemptMove();
		}
		
	}
	
	// This method is called when the robot dies
	public void onDeath()
	{
		
	}
	
	public void attemptMove()
	{
		if(frontIsClear())
		{
			move();
		}
		else if(abandonMove == 10)
		{
			return;
		}
		else
		{
			abandonMove++;
			turnRandom();
			attemptMove();
		}
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
