package teams.starter.wander;

import org.newdawn.slick.SlickException;

import files.Images;
import item.StoneBlock;
import item.equipment.UpgradePlating;
import item.equipment.UpgradeShield;
import item.weapon.ranged.WeaponLaser;
import item.weapon.ranged.WeaponSniper;
import objects.Frame;
import objects.Robot;
import world.cells.feature.Stone;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class WanderRobotSniper extends WanderRobot 
{
	final int TURN_TIME = 50;
	int turnTimer = 0;

	public WanderRobotSniper(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
		this.setFrame(Frame.MEDIUM);
		setName("Sniper");
		buy(new WeaponSniper(this));
		buy(new UpgradePlating(this));
		buy(new UpgradeShield(this));
		buy(new UpgradeShield(this));
		
	}
	
	
	
}
