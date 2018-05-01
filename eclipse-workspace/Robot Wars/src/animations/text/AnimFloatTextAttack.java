package animations.text;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import files.Fonts;
import objects.GameObject;
import objects.Unit;
import values.DisplayValues;
import values.CoreValues;

public class AnimFloatTextAttack extends AnimFloatText{
	
	int actualValue;

	public AnimFloatTextAttack(String text, GameObject u)
	{
		super(text, u, DisplayValues.COMBAT_FLOAT_TEXT_DURATION);
		actualValue = -1;
		formatText();
		setFont();
	}
	
	public AnimFloatTextAttack(String text, GameObject u, int delay)
	{
		super(text, u, DisplayValues.COMBAT_FLOAT_TEXT_DURATION, delay);
		actualValue = -1;
		formatText();
		setFont();
	}
	
	public AnimFloatTextAttack(float damage, GameObject u)
	{
		super(""+ Math.round(damage), u, DisplayValues.COMBAT_FLOAT_TEXT_DURATION);
		actualValue = Math.round(damage);
		formatText();
		setFont();
	}
	
	public void formatText()
	{
		if(actualValue == -1)
		{
			return;
		}
		
		if(actualValue == 0)
		{
			text = "Miss!";
		}
	}
	
	public void setFont()
	{
		if(actualValue <= 3)
		{ 
			myFont =  Fonts.calibri12Bold;
		}
		else if(actualValue > 3 && actualValue < 10)
		{ 
			myFont =  Fonts.calibri14Bold;
		}
		else
		{
			myFont = Fonts.calibri18Bold;
		}
	}


}
