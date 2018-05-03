package teams.starter.approach;

import org.newdawn.slick.SlickException;

import files.Images;
import item.StoneBlock;
import item.equipment.UpgradePlating;
import item.equipment.UpgradeShield;
import item.weapon.ranged.WeaponLaser;
import item.weapon.ranged.WeaponMachineGun;
import item.weapon.ranged.WeaponSniper;
import objects.Frame;
import objects.Robot;
import world.cells.feature.Stone;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class ApproachRobotMG extends ApproachRobot 
{

	public ApproachRobotMG(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
		this.setFrame(Frame.LIGHT);				
		setName("Gunner");
		
		buy(new WeaponMachineGun(this));	
		
		buy(new UpgradePlating(this));
		buy(new UpgradePlating(this));
		buy(new UpgradePlating(this));
		

	}
	
	
	
}
