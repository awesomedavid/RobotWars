package animations;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import animations.Animation;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import values.CoreValues;

public class AnimProjectileSniper extends AnimProjectile
{	
	//final static int SPEED = 12;
	float[] xOld;
	float[] yOld;
	Color closeColor;
	
	public AnimProjectileSniper(Point origin, Unit actor, GameObject target, boolean hit) {
		super(origin, actor, target, WEAPON_SNIPER_ANIMATION_SPEED, hit);
		
		xOld = new float [getDuration()];
		yOld = new float [getDuration()];
		
		
		for(int i = 0; i < xOld.length; i++)
		{
			xOld[i] = getActor().getXPixel();
			yOld[i] = getActor().getYPixel();
		}
		
		closeColor = getActor().getTeam().getColor();
		closeColor = new Color(closeColor.getRed(), closeColor.getGreen(), closeColor.getBlue(), 100);
		
	}
	
	public void update()
	{
		super.update();
		
		if(getTicks() < getDuration())
		{
			xOld[getTicks()-1] = x;
			yOld[getTicks()-1] = y;
		}

		
	}
	
	public void render(Graphics g) 
	{		
		g.setColor(getColor());
		g.fillOval(x, y, 5, 5);
		
		g.setLineWidth(2);
		g.setColor(Color.black);
		g.drawOval(x, y, 5, 5);

		
		int far = (int) (.4f * getDuration()) + 1;
		
		if(getTicks() > far)
		{
			g.setLineWidth(3);
			g.setColor(new Color(200, 200, 200, 50));
			g.drawLine(x,  y, xOld[getTicks()-far], yOld[getTicks()-far]);
		}
		
		int close = (int) (.3f * getDuration()) + 1;
		
		if(getTicks() > close)
		{
			g.setLineWidth(1);
			g.setColor(closeColor);
			g.drawLine(x,  y, xOld[getTicks()-close], yOld[getTicks()-close]);
		}
		
		g.setLineWidth(1);

	}

}
