import java.util.ArrayList;
import java.util.Objects;

import org.newdawn.slick.Image;

public abstract class GameObject {

	protected float x,y;
	protected Image im;
	protected float imW,imH;
	GameObject(float x, float y, Image im){
		this.x = x;
		this.y = y;                                                                                                                                                                     
		this.im = im.copy();
	}
	protected boolean collision(float leftX,float rightX,float topY, float botY) {			
		return containsPoint(leftX, topY) || containsPoint(rightX, topY) || containsPoint(leftX, botY) || containsPoint(rightX, botY);
	}
	
	private boolean containsPoint(float tX, float tY) {
		return (tX >= x && tX <= x + im.getWidth() && tY >= y && tY <= y + im.getHeight()); 
	}

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getImW() {
		return imW;
	}
	public void setImW(float imW) {
		this.imW = imW;
	}
	public float getImH() {
		return imH;
	}
	public void setImH(float imH) {
		this.imH = imH;
	}
	
}
