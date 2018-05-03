package values;

import org.newdawn.slick.Color;

public interface CoreValues 
{ 
	public final static int FRAMES_PER_SECOND = 60;
	
	public final static int RESOLUTION_X = 1921; 	// We're using a weird resolution for the projector
	public final static int RESOLUTION_Y = 1081; 	// You can change this to fit your home system better
	
	public final static int TRANSITION_FADE_TIME = 60;
	public final static int TRANSITION_FADE_TIME_SLOW = 240;
	
	public final static int BASE_CHOP_SKILL = 4;
	public final static int BASE_MINE_SKILL = 4;
	
	public final static int TICK_TIME = Math.max(1, 10 - Settings.gameSpeed);
	
	public final static int CELL_SIZE = 32;

	public static final int PLAYER_START_EDGE = 7;

	public static final int TEAM_STARTING_METAL = 200;
	public static final int TEAM_STARTING_STONE = 0;
	public static final int TEAM_STARTING_WOOD = 0;

	public final static int CONTROL_ZONE_MIN_DISTANCE = 25;
	public static final int CONTROL_ZONE_NUMBER = 3;
	public static final int CONTROL_ZONE_SIZE = 5;
	

	public static final int POINTS_FOR_KILL = 10;		// Friendly kill awards points to all opponents
	public static final int POINTS_TO_WIN = 1000;
	public static final int POINT_GAIN_FREQUENCY = 60;



}
