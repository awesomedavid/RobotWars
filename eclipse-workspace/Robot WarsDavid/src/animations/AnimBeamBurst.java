package animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.Animation;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;

public abstract class AnimBeamBurst extends AnimBeam {

	public AnimBeamBurst(Point origin, Unit actor, GameObject target, int width, int duration, boolean hit) {
		super(origin, actor, target, width, duration, hit);
	}
	
	public Color getColor(int r, int g, int b)
	{
		return new Color(r, g, b, getFadeAlphaValue());
	}
	
	public int getWidth()
	{
		return (int) (width * (1.0 - percentComplete()));
	}

}
