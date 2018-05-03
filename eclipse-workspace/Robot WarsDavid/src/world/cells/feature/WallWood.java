package world.cells.feature;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import files.Images;
import objects.Point;
import objects.Unit;
import world.World;
import world.cells.Cell;

public class WallWood extends Wall
{	
	public WallWood(Cell owner)
	{
		super(owner);
		curHealth = WALL_HEALTH;
		maxHealth = curHealth;
		sheet = Images.wallBrick;
	}

	public void shadeColor() {
		image.setImageColor(.5f,  .25f, .05f);
	}

	@Override
	public Color getColor() {
		return 	new Color(180, 105, 60);
	}

	
	
}
