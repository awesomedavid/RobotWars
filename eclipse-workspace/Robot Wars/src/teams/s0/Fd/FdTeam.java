package teams.s0.Fd;

import java.util.Random;

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
<<<<<<< HEAD
		setName("Floppy");
		myColor = new Color(0);
=======
		setName("Floppy Disk");
		myColor = new Color(200,200,200);
>>>>>>> 0f7a6562888682d431489e0fea2bd818e4f4597e
	}

	public Color getColor() {
		return myColor;
<<<<<<< HEAD
		//return new Color((int) (Math.random() * 255),(int) (Math.random() * 255),(int) (Math.random() * 255));
=======
		
>>>>>>> 0f7a6562888682d431489e0fea2bd818e4f4597e
	}
	
//	@Override
//	public String getName() {
//		return "sec";
//	}
//	
//	@Override
//	public String toString() {		
//		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
//        StringBuilder salt = new StringBuilder();
//        Random rnd = new Random();
//        while (salt.length() < 18) { // length of the random string.
//            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
//            salt.append(SALTCHARS.charAt(index));
//        }
//        String saltStr = salt.toString();
//        return saltStr;
//	}

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
