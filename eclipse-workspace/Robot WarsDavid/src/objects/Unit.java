package objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

import actions.Action;
import actions.Chop;
import actions.Mine;
import actions.Move;
import actions.Pickup;
import actions.Reload;
import actions.attack.AttackLaser;
import actions.build.Build;
import actions.build.BuildFloor;
import actions.build.BuildFloorStone;
import actions.build.BuildFloorWood;
import actions.build.BuildWall;
import actions.build.BuildWallStone;
import actions.build.BuildWallWood;
import core.Game;
import core.Utility;
import effects.Effect;
import effects.Stun;
import files.Fonts;
import files.Images;
import item.Item;
import item.weapon.Weapon;
import values.ActionValues;
import values.CoreValues;
import values.DisplayValues;
import values.RobotValues;
import values.Settings;
import world.World;
import world.cells.Cell;
import world.cells.feature.Feature;
import world.cells.feature.Stone;
import world.cells.feature.Tree;
import world.cells.feature.Wall;
import world.cells.feature.WallStone;
import world.cells.terrain.FloorStone;

public abstract class Unit extends GameObject implements ActionValues
{
	private Inventory items;
	private Action myAction;
	private float curHeat;
	private float maxHeat;
	private float coolRate;
	private int processerLevel;
	private boolean canMine;
	private boolean canChop;
	private float mineSkill = 0;
	private float chopSkill = 0;
	protected float moveSpeed;
	private Latency latency;
	
	public Unit(int x, int y, int teamID) throws SlickException {
		super(x, y, teamID);
		sheet = Images.robotDefault;
		setImage(sheet.getSprite(0, 0));
		latency = new Latency();
		getCell().addUnit(this);
		items = new Inventory(this);
		maxHeat = RobotValues.ROBOT_HEAT_BASE;
		curHeat = 0;
		coolRate = RobotValues.ROBOT_COOL_RATE_BASE;
		processerLevel = 1;

	}
	
	public void setImage(Image i)
	{
		image = i.copy();
		image.setImageColor(getTeam().getColor().getRed()/255.0f, 
							getTeam().getColor().getGreen()/255.0f, 
							getTeam().getColor().getBlue()/255.0f);
	}
	
	public Cell look()
	{
		return look(getDirection());
	}
	
	public Cell look(Direction d)
	{
		int x = getX();
		int y = getY();
		
		if(d == Direction.WEST)
		{
			x = getX() - 1;
		}
		else if(d == Direction.NORTH)
		{
			y = getY() - 1;
		}
		else if(d == Direction.EAST)
		{
			x = getX() + 1;
		}		
		else if(d == Direction.SOUTH)
		{
			y = getY() + 1;
		}
		
		if(World.inBounds(x, y))
		{
			return World.getCell(x, y);
		}
		else
		{
			return null;
		}
	}
	
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float getCurHeat()
	{
		return curHeat;
	}
	
	public float getMaxHeat()
	{
		return maxHeat;
	}
	
	public float getCoolRate()
	{
		return coolRate;
	}
	
	public boolean isOverheated()
	{
		return curHeat > maxHeat;
	}
	
	public void addHeat(float amount)
	{
		curHeat += amount;
	}
	
	public Inventory getInventory()
	{
		return items;
	}
	

	public void reduceHeat(float amount)
	{
		curHeat = Math.max(0, curHeat - amount);
	}
	
	public boolean hasAction()
	{
		return myAction != null;
	}
	
	public Action getAction()
	{
		return myAction;
	}
	
	public boolean readyToAct()
	{
		return isAlive() && !isOverheated() && !hasAction() && !hasEffect(Stun.class);
	}
		
	public void clearAction()
	{
		if(myAction != null)
		{
			myAction.cancel();
		}
		myAction = null;
	}
	
	public boolean hasItem()
	{
		return !items.isEmpty();
	}
	
	public boolean hasPrimary()
	{
		return items.hasPrimary();
	}
	
	public boolean hasSecondary()
	{
		return items.hasSecondary();
	}
	
	public Weapon getPrimary()
	{
		return items.getPrimary();
	}
	
	public Weapon getSecondary()
	{
		return items.getSecondary();
	}
	
	public void usePrimary()
	{
		getPrimary().use();
	}
	
	public void usePrimary(GameObject o)
	{
		getPrimary().use(o);
	}
	
	public void usePrimary(Point p)
	{
		getPrimary().use(p);
	}
	
	public void useSecondary()
	{
		getSecondary().use();
	}
	
	public void useSecondary(GameObject o)
	{
		getSecondary().use(o);
	}
	
	public void useSecondary(Point p)
	{
		getSecondary().use(p);
	}
	
	public boolean canEnter(int x, int y)
	{
		return World.canEnter(x,  y, this);
	}
	
	public boolean canEnter(Cell t)
	{
		return World.canEnter(t.getX(), t.getY(), this);
	}


	public void increaseBaseSpeed(float amount)
	{
		moveSpeed += amount;
	}
	
	public void increaseMaxHealth(int amount)
	{
		maxHealth += amount;
		
		if(maxHealth > RobotValues.MAXIMUM_HEALTH_CAP)
		{
			maxHealth =  RobotValues.MAXIMUM_HEALTH_CAP;
		}
		
		curHealth = maxHealth;		
	}
	
	public void increaseMaxPlating(int amount)
	{
		maxPlating += amount;	
		
		if(maxPlating > RobotValues.MAXIMUM_PLATING_CAP)
		{
			maxPlating = RobotValues.MAXIMUM_PLATING_CAP;
		}
		
		curPlating = maxPlating;
	}
	
	public void increaseMaxShield(int amount)
	{
		maxShield += amount;
		
		if(maxShield > RobotValues.MAXIMUM_SHIELD_CAP)
		{
			maxShield = RobotValues.MAXIMUM_SHIELD_CAP;
		}
		
		curShield = maxShield;
	}
	
	public void increaseShieldRegen(int amount)
	{
		shieldRegenAmount += amount;	
	}

	public boolean inRange(GameObject target, Weapon w)
	{
		return Utility.distance(x, y, target.getX(), target.getY()) <= w.getRange();
	}

	public boolean inZone()
	{
		return getCell().hasZone();
	}
	
	public boolean inZoneFriendly()
	{
		return inZone() && getCell().getZone().getOwner() == this.getTeam();
	}
	
	public boolean inZoneNeutral()
	{
		return inZone() && getCell().getZone().isNeutral();
	}
	
	public boolean inZoneHostile()
	{
		return inZone() && !inZoneNeutral() && getCell().getZone().getOwner() != this.getTeam();
	}
	
	/******************************** UNIT DETECTION *********************************/
	
	
	public int getDistance(GameObject o)
	{
		return Utility.distance(this, o);
	}
	
	// Gets all enemies
	public ArrayList<Unit> getEnemies() {
		return getEnemies(Unit.class);
	}
	
	// Gets all enemies of a given class
	public ArrayList<Unit> getEnemies(Class<? extends Unit> clazz) {

		ArrayList<Unit> allEnemies = new ArrayList<Unit>();
		for (Unit a : Game.getUnits()) {
			if (clazz.isInstance(a) && a.getTeam() != getTeam()) {
				allEnemies.add(a);
			}
		}
		return allEnemies;
	}
	
	// Gets the nearest enemy
	public Unit getNearestEnemy() {
		return getNearestEnemy(Unit.class);
	}

	// Gets the nearest enemy of a given class
	public Unit getNearestEnemy(Class<? extends Unit> clazz) {

		float nearestDistance = Float.MAX_VALUE;
		Unit nearestTarget = null;

		for (Unit a : getEnemies(clazz)) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}
		return nearestTarget;
	}

	// Gets the nearest enemy unless it belongs to a given class
	public Unit nearestEnemyExclude(Class<? extends Unit> clazz) {

		float nearestDistance = Float.MAX_VALUE;
		Unit nearestTarget = null;

		for (Unit a : getEnemies(clazz)) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}

		return nearestTarget;
	}

	// Count the enemies within a given radius
	public int countEnemiesInRadius(float radius) {
		return getEnemiesInRadius(radius, Unit.class).size();
	}

	// Gets the enemies in a given radius
	public ArrayList<Unit> getEnemiesInRadius(float radius) {
		return getEnemiesInRadius(radius, Unit.class);
	}

	// Gets the enemies in a given radius of a given type
	public ArrayList<Unit> getEnemiesInRadius(float radius, Class<? extends Unit> clazz) {
		ArrayList<Unit> radiusEnemies = new ArrayList<Unit>();
		
		for (Unit e : getEnemies(clazz)) {
			if (Utility.distance(this, e) <= radius) {
				radiusEnemies.add(e);
			}
		}
		return radiusEnemies;
	}
	
	/******************************** SKILLS ******************************************/
	
	public void enableMine()
	{
		canMine = true;
		mineSkill = BASE_MINE_SKILL;
	}
	
	public void enableChop()
	{
		canChop = true;
		chopSkill = BASE_CHOP_SKILL;
	}
	
	public boolean canMine()
	{
		return canMine;
	}
	
	public boolean canChop()
	{
		return canChop;
	}
	
	public float getMiningSkill()
	{
		return mineSkill;
	}
	
	public float getChopSkill()
	{
		return chopSkill;
	}
	
	public float getMoveSpeed()
	{
		return moveSpeed;
	}
	
	/******************************** ACTIONS ******************************************/
	
	public void setAction(Action a)
	{
		if(readyToAct())
		{
			myAction = a;
		}
	}
	
	public void move()
	{		
		if(readyToAct() && canEnter(look()))
		{
			setAction(new Move(this));		
		}
	}
	
	public void mine()
	{
		Cell next = look();
		
		if(readyToAct() && canMine() && next != null && next.hasFeature() && next.getFeature() instanceof Stone)
		{
			setAction(new Mine(this, (Stone) next.getFeature()));		
		}
	}
	
	public void chop()
	{
		Cell next = look();
		
		if(readyToAct() && canChop() && next != null && next.hasFeature() && next.getFeature() instanceof Tree)
		{
			setAction(new Chop(this, (Tree) next.getFeature()));		
		}
	}
	
	public void pickup()
	{
		if(readyToAct() && getCell().hasItem())
		{
			setAction(new Pickup(this));
		}
	}
	
	public void build(Class<?> clazz)
	{
		if(WallStone.class.isAssignableFrom(clazz))
		{
			buildWallStone();
		}
		else if(FloorStone.class.isAssignableFrom(clazz))
		{
			buildFloorStone();
		}			
	}
	
	
	public void reload(Weapon w)
	{
		if(readyToAct() && w.usesAmmo())
		{
			setAction(new Reload(this, w));
		}
	}	

	
	public void buildWallStone()
	{
		if(readyToAct() && getTeam().hasStone(BUILD_WALL_STONE_COST) && !getCell().hasFeature() && !getCell().hasItem())
		{
			setAction(new BuildWallStone(this));	
			getTeam().loseStone(BUILD_WALL_STONE_COST);
		}
	}
	
	public void buildWallWood()
	{
		if(readyToAct() && getTeam().hasWood(BUILD_WALL_WOOD_COST) && !getCell().hasFeature() && !getCell().hasItem())
		{
			setAction(new BuildWallWood(this));	
			getTeam().loseWood(BUILD_WALL_WOOD_COST);
		}
	}
	
	public void buildFloorStone()
	{
		if(readyToAct() && getTeam().hasStone(BUILD_FLOOR_STONE_COST) && !getCell().hasFeature())
		{
			setAction(new BuildFloorStone(this));
			getTeam().loseStone(BUILD_FLOOR_STONE_COST);
		}
	}
	
	public void buildFloorWood()
	{
		if(readyToAct() && getTeam().hasWood(BUILD_FLOOR_WOOD_COST) && !getCell().hasFeature())
		{
			setAction(new BuildFloorWood(this));
			getTeam().loseWood(BUILD_FLOOR_WOOD_COST);
		}
	}
	
	abstract public void action();
	
	public long getRecentLatency()
	{
		return latency.getRecentLatency();
	}
	public void addLatency(long amount)
	{
		latency.addLatency(amount);
	}
	
	public int getOverclockLevel()
	{
		return RobotValues.OVERCLOCK_LEVEL_PER_PROCESSOR * processerLevel;
	}
	
	public void update()
	{
		super.update();		
		
		if(isAlive())
		{
			reduceHeat(coolRate);
		}
		if(getRecentLatency() > RobotValues.OVERCLOCK_LEVEL_PER_PROCESSOR * processerLevel)
		{
			addHeat(RobotValues.ROBOT_OVERCLOCK_RATE);
		}

		items.update();

		// If I have no action queued and I am alive, subclass defines behavior
		if(readyToAct())
		{
			action();
		}
		
		// Do nothing if stunned
		else if(hasEffect(Stun.class))
		{
		
		}
		
		// Set stunned if overheated
		else if(isOverheated())
		{
			float excessHeatDissipateTime = (getCurHeat() - getMaxHeat()) / getCoolRate();
			float fullHeatDissipateTime = (getMaxHeat() / getCoolRate());
			int stunTime = (int) (excessHeatDissipateTime + fullHeatDissipateTime);
			//System.out.println(stunTime);
			addEffect(new Stun(this, this, 0, stunTime));
		}
		
		else if(myAction == null)
		{
			// No action, but stunned or something - do nothing
		}
		
		// If I'm interrupted or finished, clear out my action
		else if(myAction.isCancelled() || !isAlive())
		{
			clearAction();
		}
		
		// Otherwise, continue current action
		else
		{
			myAction.update();
			
			// this fixes movement, but it's causing units to sometimes fire after death.... 
			if(myAction.isComplete())
			{
				clearAction();
			}
		}
			
	}
	
	public void die()
	{
		super.die();
		clearAction();
		curHeat = 0;
	}
	

	
	public int getXPixel()
	{
		return x * CoreValues.CELL_SIZE + getMovingOffset().getX();
	}

	public int getYPixel()
	{
		return y * CoreValues.CELL_SIZE + getMovingOffset().getY();
	}
	
	
	public Point getMovingOffset()
	{
		int xOff = 0;
		int yOff = 0;
		
		if(myAction instanceof Move)
		{
			if(facing(Direction.WEST))
			{
				xOff = (int) (-CoreValues.CELL_SIZE * myAction.getPercentComplete());
			}
			else if(facing(Direction.NORTH))
			{
				yOff = (int) (-CoreValues.CELL_SIZE  * myAction.getPercentComplete());
			}
			else if(facing(Direction.EAST))
			{
				xOff = (int) (CoreValues.CELL_SIZE  * myAction.getPercentComplete());
			}
			else if(facing(Direction.SOUTH))
			{
				yOff = (int) (CoreValues.CELL_SIZE * myAction.getPercentComplete());
			}

		}

		return new Point(xOff, yOff);
		
	}
	

	public void render(Graphics g)
	{		
		if (isAlive() && image != null) 
		{
			if(theta > 360)
			{
				theta %= 360;
			}
			
		
			setImage(sheet.getSprite((int) (theta / 90), 0));
			
			
		
			
//			image.setCenterOfRotation(image.getWidth() / 2, image.getHeight() / 2);
	//		image.setRotation(theta);
			
			float xOff = getMovingOffset().getX();
			float yOff = getMovingOffset().getY();
			
			image.draw(x * CoreValues.CELL_SIZE + xOff, y * CoreValues.CELL_SIZE + yOff - CoreValues.CELL_SIZE);
				
			renderHealthbars(g);
			renderAction(g);
			renderHealthValues(g);			
			renderOverheat(g);
		}	
		

	}
	
	public void renderOverheat(Graphics g)
	{
		if(hasEffect(Stun.class))
		{
			float xOff = getMovingOffset().getX() + CELL_SIZE / 2;
			float yOff = getMovingOffset().getY() + CELL_SIZE / 2;
			
			g.setFont(Fonts.arialblack28);
			g.setColor(Color.black);
			String message = "!";
			
			int w = Fonts.arialblack28.getWidth(message);
			int h = Fonts.arialblack28.getHeight(message);

			g.drawString(message, x*CELL_SIZE+xOff+2-w/2, y*CELL_SIZE-CELL_SIZE*2+yOff+2-h/2);
			g.setColor(new Color(255, 100, 0));
			g.drawString(message, x*CELL_SIZE+xOff-w/2, y*CELL_SIZE-CELL_SIZE*2+yOff-h/2);
		}
	}
	
	public void renderHealthValues(Graphics g)
	{ 
		if(Settings.showHealthValues)
		{
			float xOff = getMovingOffset().getX();
			float yOff = getMovingOffset().getY();
			
			g.setFont(Fonts.calibri12);
			g.setColor(DisplayValues.COLOR_SHIELD);
			g.drawString(""+(int)getCurShield(), x*CELL_SIZE+xOff, y*CELL_SIZE+32+yOff);
			g.setColor(DisplayValues.COLOR_PLATING);
			g.drawString(""+(int)getCurPlating(), x*CELL_SIZE+xOff, y*CELL_SIZE+32+yOff+12);
			g.setColor(DisplayValues.COLOR_HEALTH);
			g.drawString(""+(int)getCurHealth(), x*CELL_SIZE+xOff, y*CELL_SIZE+32+yOff+24);
		}
	}
	
	public void renderAction(Graphics g)
	{
		if(Settings.showAction)
		{
			float xOff = getMovingOffset().getX();
			float yOff = getMovingOffset().getY();
			
			g.setFont(Fonts.calibri12);
			g.setColor(Color.white);
			g.drawString(""+getAction(), x*CELL_SIZE+xOff, y*CELL_SIZE+32+yOff);
		}
	}
	
	public void renderHealthbars(Graphics g)
	{	
		if(Settings.showHealthbars)
		{
			float xOff = getMovingOffset().getX();
			float yOff = getMovingOffset().getY();
			
		final int WIDTH = CELL_SIZE - 2;
		
		if(curHealth > 0)
		{
			renderBar(g, WIDTH, DisplayValues.COLOR_HEALTH.darker(.40f));
			renderBar(g, (int) (getCurHealth() / getMaxHealth() * WIDTH),  DisplayValues.COLOR_HEALTH);
		}
		
		// Plating 
		if(curPlating > 0)
		{
			renderBar(g, CELL_SIZE-2,  DisplayValues.COLOR_PLATING.darker(.40f));
			renderBar(g, (int) (getCurPlating() / getMaxPlating() * WIDTH),  DisplayValues.COLOR_PLATING);
		}
		
		// Shield 
		if(curShield > 0)
		{
			renderBar(g, CELL_SIZE-2,  DisplayValues.COLOR_SHIELD.darker(.40f));
			renderBar(g,(int) (getCurShield() / getMaxShield()  * WIDTH),  DisplayValues.COLOR_SHIELD);
		}
		}
	}
	
	private void renderBar(Graphics g, int width, Color c)
	{
		g.setColor(c);
		g.fillRect(getXPixel()+1, getYPixel() - 8 - CoreValues.CELL_SIZE, width, 5);
		g.setColor(Color.black);
		g.drawRect(getXPixel()+1, getYPixel() - 8 - CoreValues.CELL_SIZE, width, 5);
	}

}