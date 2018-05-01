package core;

import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
import objects.Team;
import teams.starter.approach.ApproachTeam;
import teams.starter.wander.WanderTeam;
import values.CoreValues;
import world.cells.Cell;
import world.cells.feature.Feature;

public class Select extends BasicGameState {
	private StateBasedGame sbg;
	private int id;

	private static List<Team> teams;

	Select(int id) {
		this.id = id;
		teams = new ArrayList<Team>();
	}

	public static List<Team> getTeams() {
		return teams;
	}

	public static void addTeam(Class<? extends Team> clazz) {
		try {
			Team t = (Team) (clazz.getConstructor(Integer.TYPE)).newInstance(teams.size());
			getTeams().add(t);

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.sbg = sbg;

		addTeam(ApproachTeam.class);
		addTeam(WanderTeam.class);

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// String m = "HIGHWAY TO THE DANGER ZONE";
		// TrueTypeFont f = Fonts.calibri32;
		// g.setFont(f);
		// g.setColor(new Color(220, 220, 220, 255));
		// g.drawString(m, CoreValues.RESOLUTION_X/2 - f.getWidth(m)/2,
		// CoreValues.RESOLUTION_Y/2 - f.getHeight(m)/2);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		sbg.enterState(Engine.GAME_ID, new FadeOutTransition(Color.black, CoreValues.TRANSITION_FADE_TIME),
				new FadeInTransition(Color.black, CoreValues.TRANSITION_FADE_TIME));

	}

	public void enter() {

	}

	public void keyReleased(int key, char c) {
		//
		// sbg.enterState(Engine.GAME_ID,
		// new FadeOutTransition(Color.black, CoreValues.TRANSITION_FADE_TIME_SLOW),
		// new FadeInTransition(Color.black, CoreValues.TRANSITION_FADE_TIME_SLOW));
	}

	public int getID() {
		return id;
	}

}
