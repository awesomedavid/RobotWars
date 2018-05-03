package world.cells.feature;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import files.Images;
import objects.Point;
import objects.Unit;
import world.World;
import world.cells.Cell;

public class Stone extends Harvestable
{
	final float STONE_HEALTH = 100;
	Point spritePos;
	public Stone(Cell owner)
	{
		super(owner);
		isObstacle = true;
		blocksSight = true;
		curHealth = STONE_HEALTH;
		maxHealth = curHealth;
		sheet = Images.stone;
		spritePos = new Point(-1, -1);
		canMine = true;
		isCover = true;
		

		
	}
	
	public void update()
	{
		Point temp = getSpriteCoordinate();
		if(!spritePos.equals(temp))
		{
			spritePos = temp;
			image = sheet.getSprite(spritePos.getX(), spritePos.getY());
			
			float r = .8f - ((1-owner.getMoisture())*.2f);
			float g = .8f - ((1-owner.getMoisture())*.35f);
			float b = .8f - ((1-owner.getMoisture()) * .45f);
			image.setImageColor(r, g ,b );
		}

	}
	
	public Color getHiddenColor()
	{
		
		float rMod = .9f - ((1-owner.getMoisture())*.2f);
		float gMod = .9f - ((1-owner.getMoisture())*.35f);
		float bMod = .9f - ((1-owner.getMoisture()) * .45f);
		return new Color((int) (110 * rMod), (int) (115 * gMod), (int)(110 * bMod));
	}

	@Override
	public Color getColor() {
		
		float rMod = .9f - ((1-owner.getMoisture())*.2f);
		float gMod = .9f - ((1-owner.getMoisture())*.35f);
		float bMod = .9f - ((1-owner.getMoisture()) * .45f);
		return new Color((int) (130 * rMod), (int) (125 * gMod), (int)(120 * bMod));
	}
	

	

	
	

}
