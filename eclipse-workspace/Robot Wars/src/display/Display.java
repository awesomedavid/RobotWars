package display;

import org.newdawn.slick.Graphics;

import core.Game;
import display.duo.DisplayDuoRobot;
import display.hud.DisplayTeam;
import objects.Robot;
import values.CoreValues;
import values.Settings;

public class Display 
{
	Minimap minimap;
	GameSpeed gameSpeed;

	
	public Display()
	{
		minimap = new Minimap();
		gameSpeed = new GameSpeed();
	}
	
	public void update()
	{
		if(Settings.showMiniMap)
		{
			minimap.update();
		}
			
		if(Settings.showHUD)
		{
			gameSpeed.update();

		}
	}
	
	public void render(Graphics g)
	{
		if(Settings.showMiniMap)
		{
			minimap.render(g);
		}
		
		if(Settings.showHUD)
		{
			gameSpeed.render(g);
		}

	}
	


}
