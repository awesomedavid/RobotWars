package teams.starter.approach;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

import core.Utility;
import objects.Robot;
import objects.Team;

public class ApproachTeam extends Team
{
	Color myColor;
	public ApproachTeam(int id) {
		super(id);
		setName("Approach");
		myColor = new Color(Utility.random(255), Utility.random(255), Utility.random(255));
	}

	public Color getColor() 
	{
		return myColor;
	}
	
	public Robot createRobotOne(int x, int y) throws SlickException
	{
		return new ApproachRobotMG(x, y, getID(), 1);
	}

	protected Robot createRobotTwo(int x, int y) throws SlickException {
		return new ApproachRobotMG(x, y, getID(), 2);
	}

	protected Robot createRobotThree(int x, int y) throws SlickException {
		return new ApproachRobotMG(x, y, getID(), 3);
	}

	protected Robot createRobotFour(int x, int y) throws SlickException {
		return new ApproachRobotMG(x, y, getID(), 4);
	}
}
