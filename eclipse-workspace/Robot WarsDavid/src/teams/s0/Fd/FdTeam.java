package teams.s0.Fd;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

import core.Utility;
import objects.Robot;
import objects.Team;

public class FdTeam extends Team {
	Color myColor;
	private int lol;
	public FdTeam(int id) {
		super(id);
		setName("Floppy Disk");
		myColor = new Color(200,200,200);
	}

	public Color getColor() {
		return myColor;
		
	}

	public Robot createRobotOne(int x, int y) throws SlickException {
		return new FdLaser(x, y, getID(), 1);
	}

	protected Robot createRobotTwo(int x, int y) throws SlickException {
		return new FdLaser(x, y, getID(), 2);
	}

	protected Robot createRobotThree(int x, int y) throws SlickException {
		return new FdLaser(x, y, getID(), 3);
	}

	protected Robot createRobotFour(int x, int y) throws SlickException {
		return new FdLaser(x, y, getID(), 4);
	}
}
