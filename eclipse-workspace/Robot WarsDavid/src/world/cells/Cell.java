package world.cells;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Utility;
import files.Images;
import item.Item;
import item.StoneBlock;
import objects.GameObject;
import objects.Unit;
import values.CoreValues;
import values.Settings;
import world.World;
import world.Zone;
import world.cells.feature.Beacon;
import world.cells.feature.Feature;
import world.cells.feature.Stone;
import world.cells.feature.Tree;
import world.cells.terrain.Grass;
import world.cells.terrain.GrassHeavy;
import world.cells.terrain.GrassLight;
import world.cells.terrain.GrassMedium;
import world.cells.terrain.Mud;
import world.cells.terrain.Sand;
import world.cells.terrain.Soil;
import world.cells.terrain.StoneRough;
import world.cells.terrain.Terrain;
import world.cells.terrain.WaterDeep;
import world.cells.terrain.WaterShallow;

public class Cell implements CoreValues
{
	Unit unit;

	private int x;
	private int y;

	private float moisture;
	private float elevation;
	private float fertility;

	private boolean isObscured;
	private GameObject reserver;


	private Zone zone;
	private Terrain terrain;
	private Feature feature;
	private List<Item> items;

	final static public float SAND_MOISTURE_THRESHOLD = .20f;
	final static public float SAND_FERTILITY_THRESHOLD = .20f;

	final static public float GRASS_LIGHT_FERTILITY_THRESHOLD = .40f;
	final static public float GRASS_MEDIUM_FERTILITY_THRESHOLD = .55f;
	final static public float GRASS_HEAVY_FERTILITY_THRESHOLD = .75f;
	
	final static public float TREE_FERTILTY_THRESHOLD = .65f;
	final static public float SHALLOW_WATER_MOISTURE_THRESHOLD = .55f;
	final static public float SHORE_MOISTURE_THRESHOLD = .45f;

	final static public float DEEP_WATER_MOISTURE_THRESHOLD = SHALLOW_WATER_MOISTURE_THRESHOLD + .25f;

	final static public float MOUNTAIN_ELEVATION_THRESHOLD = .70f;
	final static public float ROCKY_GROUND_ELEVEATION_THRESHOLD = MOUNTAIN_ELEVATION_THRESHOLD - .05f;
	
	public Cell(int x, int y)
	{
		this.x = x;
		this.y = y;
		items = new LinkedList<Item>();	
		
	}
	
	public void clear()
	{
		if(hasZone())
		{
			zone = null;
		}
		else if(hasUnit())
		{
			removeUnit();
		}
		else if(hasFeature())
		{
			removeFeature();
		}
		else if(hasTerrain()) 
		{
			removeTerrain();
		}
	}
	
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public boolean hasFeature()
	{
		return feature != null;
	}
	
	
	public void setReservation(GameObject o)
	{
		reserver = o;
	}
	
	public void clearReservation()
	{
		reserver = null;
	}
	
	public boolean hasFeature(Class<? extends Feature> clazz)
	{
		return hasFeature() && clazz.isInstance(feature);
	}

	
	public boolean hasImpassableTerrain()
	{
		return hasTerrain() && !terrain.isPassable();
	}
	
	public boolean hasObstacle()
	{
		return hasFeature() && feature.isObstacle();
	}
	
	public boolean canEnter(GameObject actor)
	{
		return !hasObstacle() && !hasImpassableTerrain() && !hasUnit() && !isReservedByAnother(actor);
	}

	public boolean hasPermanentObstacle()
	{
		return hasFeature() && feature.isPermanent();
	}
	
	
	public boolean hasZone()
	{
		return zone != null;
	}
	
	public Zone getZone()
	{
		return zone;
	}
	
	public void setZone(Zone zone)
	{
		this.zone = zone;
	}
	
	public Feature getFeature()
	{
		return feature;
	}

	public void addFeature(Feature f)
	{
		if(feature == null)
		{
			feature = f;
		}
	}
	
	public void addTerrain(Terrain t)
	{
		if(terrain == null)
		{
			terrain = t;
		}
	}


	
	public Terrain getTerrain() {
		return terrain;
	}

	public boolean hasItem()
	{
		return !items.isEmpty();
	}

	public boolean hasItem(Class<? extends Item> clazz)
	{
		return hasItem() && clazz.isInstance(items.get(0));
	}

	public int countItems()
	{
		return items.size();
	}

	public void addItem(Item i)
	{
		if(i != null)
		{
			items.add(i);
		}
	}

	public void deleteItem()
	{
		if(hasItem())
		{
			items.remove(items.size() - 1);
		}
	}

	public Item takeItem()
	{
		if(hasItem())
		{
			return items.remove(items.size() - 1);
		}
		else
		{
			return null;
		}
	}

	/******************** GENERATION *********************/
	
	public void setMoisture(float amount)
	{
		this.moisture = amount;
	}
	
	public void setFertility(float amount)
	{
		this.fertility = amount;
	}

	public void setMountain(float amount)
	{
		this.elevation = amount;
	}
	
	public void spawnTerrain()
	{
		if(!hasTerrain())
		{
			if(moisture < SAND_MOISTURE_THRESHOLD && fertility < SAND_FERTILITY_THRESHOLD)
			{
				terrain = new Sand(this);
			}
			else if(fertility > GRASS_HEAVY_FERTILITY_THRESHOLD)
			{
				terrain = new GrassHeavy(this);
			}
			else if(fertility> GRASS_MEDIUM_FERTILITY_THRESHOLD)
			{
				terrain = new GrassMedium(this);
			}
			else if(fertility > GRASS_LIGHT_FERTILITY_THRESHOLD)
			{
				terrain = new GrassLight(this);
			}
			else
			{
				terrain = new Soil(this);
			}
		}
		
		if(!hasFeature() && terrain instanceof Grass)
		{
			float treeChance = fertility - Utility.random(0f, .6f);
			float adjPenalty = .05f;		
			
			int range = 5;
			
			
			for(int i = -range; i < range; i++)
			{
				for(int j = -range; j < range; j++)
				{
					if(World.inBounds(x+i, y+j) && World.getCell(x+i, y+j).hasFeature())
					{
						treeChance -= adjPenalty;
					}
				}
			}
//			
//			
//			if(World.inBounds(x-1, y) && World.getCell(x-1, y).hasFeature()) 
//			{
//				treeChance -= adjPenalty;
//			}
//			if(World.inBounds(x+1, y) && World.getCell(x+1, y).hasFeature()) 
//			{
//				treeChance -= adjPenalty;
//			}
//			if(World.inBounds(x, y-1) && World.getCell(x, y-1).hasFeature()) 
//			{
//				treeChance -= adjPenalty;
//			}
//			if(World.inBounds(x, y+1) && World.getCell(x, y+1).hasFeature()) 
//			{
//				treeChance -= adjPenalty;
//			}
//			if(World.inBounds(x-1, y-1) && World.getCell(x-1, y).hasFeature()) 
//			{
//				treeChance -= adjPenalty;
//			}
//			if(World.inBounds(x+1, y-1) && World.getCell(x+1, y).hasFeature()) 
//			{
//				treeChance -= adjPenalty;
//			}
//			if(World.inBounds(x+1, y-1) && World.getCell(x, y-1).hasFeature()) 
//			{
//				treeChance -= adjPenalty;
//			}
//			if(World.inBounds(x-1, y+1) && World.getCell(x, y+1).hasFeature()) 
//			{
//				treeChance -= adjPenalty;
//			}
			
			if(treeChance > TREE_FERTILTY_THRESHOLD)
			{
				feature = new Tree(this);
			}
		}
		
		
		// SILLY CONCEALMENT TESTING CODE - damp places get fog for now
//		
//		if(moisture > .6)
//		{
//			isObscured = true;
//		}
		
	}
	
	public void spawnWater()
	{
		if(!hasTerrain())
		{
			if(moisture - elevation > DEEP_WATER_MOISTURE_THRESHOLD)
			{
				terrain = new WaterDeep(this);
			}
			else if(moisture - elevation > SHALLOW_WATER_MOISTURE_THRESHOLD)
			{
				terrain = new WaterShallow(this);
			}
			else if(moisture - elevation > SHORE_MOISTURE_THRESHOLD)
			{
				if(fertility > GRASS_HEAVY_FERTILITY_THRESHOLD)
				{
					terrain = new GrassMedium(this);
				}
				else if(fertility> GRASS_MEDIUM_FERTILITY_THRESHOLD)
				{
					terrain = new GrassLight(this);
				}
				else if(fertility > GRASS_LIGHT_FERTILITY_THRESHOLD)
				{
					terrain = new Soil(this);
				}
				else
				{
					terrain = new Mud(this);
				}
			}
		}
	}	

	public void spawnMountain()
	{
		if(!hasTerrain())
		{
			if(elevation > ROCKY_GROUND_ELEVEATION_THRESHOLD)
			{
				terrain = new StoneRough(this);
			}
		}
		
		if(elevation > MOUNTAIN_ELEVATION_THRESHOLD)
		{
			feature = new Stone(this);
		}
	
	}	
	
	public void spawnControlPoint()
	{
		for(int i = -CONTROL_ZONE_SIZE; i <= CONTROL_ZONE_SIZE; i++)
		{
			for(int j = -CONTROL_ZONE_SIZE; j <= CONTROL_ZONE_SIZE; j++)
			{			
				if(Utility.distance(x, y, x+i, y + j) <= CONTROL_ZONE_SIZE)
				{
					Cell c = World.getCell(x+i, y + j);
					
					c.removeFeature();
					if(c.getTerrain() instanceof WaterDeep)
					{
						c.moisture = SHALLOW_WATER_MOISTURE_THRESHOLD;
						c.terrain = new WaterShallow(c);
					}
				}
			}
		}
		
		feature = new Beacon(this);
		
	}	

	public float getElevation()
	{
		return elevation;
	}
	
	public boolean isCover()
	{
		return hasFeature() && feature.isCover();
	}
	
	public boolean isObscured()
	{
		return isObscured;
	}
	
	public void addUnit(Unit u)
	{
		if(unit == null)
		{
			unit = u;
		}
	}

	public void removeUnit()
	{
		unit = null;
	}

	public void removeFeature()
	{
		feature = null;
	}
	
	public void removeTerrain()
	{
		terrain = null;
	}

	public boolean hasUnit()
	{
		return unit != null;
	}

	public boolean isReservedByAnother(GameObject o)
	{
		return reserver != null && reserver != o;
	}
	
	public Unit getUnit()
	{
		return unit;
	}
	
	public boolean hasTerrain()
	{
		return terrain != null;
	}

	public void update()
	{
		if(hasTerrain())
		{
			terrain.update();
		}
		if(hasFeature())
		{
			feature.update();
		}
	}

	public void renderLayerOne(Graphics g)
	{
		if(!isFullySurrounded())
		{
			if(hasTerrain())
			{
				terrain.render(g);
			}
			
			if(hasFeature() && !feature.isOversized())
			{
				feature.render(g);
			}

			if(!items.isEmpty())
			{
				g.drawImage(items.get(0).getImage(), x * CoreValues.CELL_SIZE, y * CoreValues.CELL_SIZE);
			}		
		}
		
		if(Settings.showSightBlocks && hasFeature())
		{
			feature.drawSightBlock(g);
		}
	}
	
	public void renderLayerTwo(Graphics g)
	{
		// Control Zones
		if(hasZone())
		{
			g.setColor(zone.getColor());
			g.fillRect(x * CoreValues.CELL_SIZE, y * CoreValues.CELL_SIZE, CoreValues.CELL_SIZE, CoreValues.CELL_SIZE);
		}

	}
	
	public void renderLayerThree(Graphics g)
	{

		// Oversized Features
		if(hasFeature() && feature.isOversized())
		{
			feature.render(g);
		}
		
		if(Settings.showConcealmentOverlay && isObscured())
		{
			g.setColor(new Color(0, 0, 150, 100));
			g.fillRect(x * CoreValues.CELL_SIZE, y * CoreValues.CELL_SIZE, CoreValues.CELL_SIZE, CoreValues.CELL_SIZE);
		}
		
		if(Settings.showCoverOverlay && isCover())
		{
			g.setColor(new Color(150, 0, 0, 100));
			g.fillRect(x * CoreValues.CELL_SIZE, y * CoreValues.CELL_SIZE, CoreValues.CELL_SIZE, CoreValues.CELL_SIZE);
		}
		
		if(Settings.showGridLines)
		{
			g.setColor(new Color(20, 20, 20, 30));
			g.drawRect(x * CoreValues.CELL_SIZE, y * CoreValues.CELL_SIZE, CoreValues.CELL_SIZE, CoreValues.CELL_SIZE);
		}
	

	}
	
	public boolean sameFeature(Cell t)
	{
		if(t == null)
		{
			return true;
		}
		else
		{
			return t.hasFeature() && hasFeature() && t.getFeature().getClass() == this.getFeature().getClass();
		}
		
	}

	private boolean isFullySurrounded()
	{			
		return hasObstacle() && 
				World.blocksSight(x-1, y-1) && sameFeature(World.getCell(x-1, y-1)) &&
				World.blocksSight(x-1, y) && sameFeature(World.getCell(x-1, y)) &&
				World.blocksSight(x-1, y+1) && sameFeature(World.getCell(x-1, y+1)) &&
				World.blocksSight(x, y-1) && sameFeature(World.getCell(x, y-1)) &&
				World.blocksSight(x, y+1) && sameFeature(World.getCell(x, y+1)) &&
				World.blocksSight(x+1, y-1) && sameFeature(World.getCell(x+1, y-1)) &&
				World.blocksSight(x+1, y) && sameFeature(World.getCell(x+1, y)) &&
				World.blocksSight(x+1, y+1) && sameFeature(World.getCell(x+1, y+1));				   
	}

	public int getDifficulty()
	{
		return terrain.getDifficulty();
	}

	public float getMoisture()
	{
		return moisture;
	}

	public float getFertility()
	{
		return fertility;
	}

}
