package values;

public interface WorldValues 
{
	// Terrain difficulty base values
	public static final int EASY = 5;
	public static final int AVERAGE = 10;
	public static final int HARD = 20;		
	
	// Easy Terrain

	public static final int TERRAIN_FLOOR_STONE_DIFFICULTY = EASY;
	public static final int TERRAIN_FLOOR_WOOD_DIFFICULTY = EASY;
	
	// Average Terrain
	public static final int TERRAIN_SOIL_DIFFICULTY = AVERAGE;
	public static final int TERRAIN_STONE_ROUGH_DIFFICULTY = AVERAGE;
	public static final int TERRAIN_SAND_DIFFICULTY = AVERAGE;
	
	// Hard Terrain
	public static final int TERRAIN_WATER_SHALLOW_DIFFICULTY = HARD;
	public static final int TERRAIN_MUD_DIFFICULTY = HARD;


}

