//David Morey
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Cell {
	
	private Color c;
	private float x;
	private float y;
	public int iD;
	public float timer;
	public int maxDuration;
	
	public Cell(float xPos, float yPos) {
		iD = 1;
		x = xPos;
		y = yPos;
		if(x == v.MAP_WIDTH/2 && y == v.MAP_HEIGHT/2)
		{
			iD = 2;
		}
		if(x == v.MAP_WIDTH/3 && y == v.MAP_HEIGHT/3)
		{
			iD = 4;
		}
		
	}
	public void draw(Graphics g) {
		if (iD == 1) {
			
			g.setColor(new Color(100,100,100));
			g.fillRect(x * v.cell_size, y * v.cell_size, v.cell_size, v.cell_size);
		} else if (iD == 2) {
			c = new Color(50, 50, 240);
			g.setColor(c);
			g.fillRect(x * v.cell_size, y * v.cell_size, v.cell_size, v.cell_size);

		} else if (iD == 3) {						
				timer++;			
				if(timer%v.timer == 0)maxDuration--;
				if(maxDuration < 0)iD = 1;				
			c = new Color(10, 10, 140);
			g.setColor(c);
			g.fillRect(x * v.cell_size, y * v.cell_size, v.cell_size, v.cell_size);
			
		}else if(iD == 4)
		{
	
			c = new Color(150, 10, 14);
			g.setColor(c);
			g.fillRect(x * v.cell_size, y * v.cell_size, v.cell_size, v.cell_size);
		}
	}
public void update()
{
	
}
public boolean isSnake()
{
	if(iD == 2 || iD == 3)return true;
	return false;
}
public boolean isFood()
{
	if(iD == 4)return true;
	return false;
}
	public int getMaxD() {
		return maxDuration;
	}
	public void setMaxD(int maxDuration) {
		this.maxDuration = maxDuration;
	}
	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

}
