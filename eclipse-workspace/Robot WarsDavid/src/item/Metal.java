package item;

import core.Utility;
import files.Images;

public class Metal extends Resource 
{
	public Metal()
	{
		super();
		int r = (int) (Math.random() * Images.metal.length);
		image = Images.metal[r];
		image.rotate(Utility.random(0, 360));
	}
	
}
