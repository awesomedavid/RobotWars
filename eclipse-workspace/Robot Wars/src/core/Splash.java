package core;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import files.Fonts;
import files.Images;
import values.CoreValues;


public class Splash extends BasicGameState 
{
	private StateBasedGame sbg;
	private Image splash;
	private Image splashScaled;
	private int id;
	private int step;
	private String message = "";
	
	Splash(int id)
	{
		this.id = id;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		this.sbg = sbg;
		gc.setMouseGrabbed(true);


		Fonts.calibri32 = new TrueTypeFont(new Font("Calibri", Font.BOLD, 32), false);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{

		
		String m = message;
		
		TrueTypeFont f = Fonts.calibri32;
		g.setFont(f);	
		g.setColor(new Color(220, 220, 220, 255));
		g.drawString(m, CoreValues.RESOLUTION_X/2 - f.getWidth(m)/2, CoreValues.RESOLUTION_Y - f.getHeight(m)/2 - 50);
		if(step == 0)
		{
			step = 1;
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{		
		if(step == 1)
		{	
			Fonts.loadFonts();
			step = 2;
			message = "Loading Images";
		}
		else if(step == 2)
		{
			Images.loadImages();
			step = 3;
			message = "Loading Audio";
		}
		else if(step == 3)
		{
//			Audio.loadAudio();
			step = 4;
			message = "Setting Music";
		}
		else if(step == 4)
		{
//			Audio.setRandomGameMusic();
//			Audio.loadGameMusicFile();
			step = 5;
			message = "Ready!";
		}

	}
	
	public void enter()
	{
		message = "Loading Fonts";
	}
		
	public void keyReleased(int key, char c) 
	{
		if(step == 5)
		{
//			try {
//				Audio.playGameMusic();
//			} catch (SlickException e) {
//		
//				e.printStackTrace();
//			}
			
			step = 4;
			
		sbg.enterState(Engine.SELECT_ID, 
				new FadeOutTransition(Color.black, CoreValues.TRANSITION_FADE_TIME), 
				new FadeInTransition(Color.black, CoreValues.TRANSITION_FADE_TIME));

		}
	}

	public int getID() 
	{
		return id;
	}

}
