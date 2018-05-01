package item.weapon;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;

import objects.GameObject;
import objects.Unit;
import world.World;
import world.cells.Cell;
import world.cells.feature.Wall;

public class ShotCalculator {
	
	public static float getCoverPenalty(int hitNum)
	{
		// CLEAN SHOT
		if(hitNum == 4)
		{
		//System.out.println("No cover");
			return 0;
		}
		
		// COVER
		else if(hitNum == 2 || hitNum == 3)
		{
		//System.out.println("Partial cover");
			return .5f;

		}
		
		// BLOCKED
		else
		{
	//	System.out.println("Full cover");
			return 1;
		}
	}
	
	public static float getConcealPenalty(int hitNum)
	{
		// CLEAN SHOT
		if(hitNum == 4)
		{
		//System.out.println("No concealment");
			return 0;
		}
		
		// COVER
		else if(hitNum == 2 || hitNum == 3)
		{
		//System.out.println("Partial concealment");
			return .5f;

		}
		
		// BLOCKED
		else
		{
		//System.out.println("Full concealment");
			return 1;
		}
	}
	
	private static Point[] getUnitCorners(GameObject u) {
		Point[] corners = {
				new Point(u.getX(), u.getY()),
				new Point(u.getX() + 1, u.getY()),
				new Point(u.getX(), u.getY() + 1),
				new Point(u.getX() + 1, u.getY() + 1)
		};
		
		return corners;
	}
	
	private static boolean isCellObstructed(int x, int y) {
		return !World.inBounds(x, y) || World.getCell(x, y).isCover();
	}
	
	private static boolean isCellConcealed(int x, int y) {
		return !World.inBounds(x, y) || World.getCell(x, y).isObscured();
	}
	
	private static boolean isLineObstructed(Line l, Cell start, Cell end, Unit unit, GameObject target) {
		// Case for vertical line
		if(l.getStart().getX() == l.getEnd().getX()) {
			for(int y = (int) Math.min(l.getStart().getY(), l.getEnd().getY()); y < Math.max(l.getStart().getY(), l.getEnd().getY()); y++) {
				if(target.getY() == y) {
					return false;
				}
				if(isCellObstructed((int)l.getStart().getX(), y) && isCellObstructed((int)l.getStart().getX(), y))
					return true;
			}
		}
		// Case for horizontal line
		else if(l.getStart().getY() == l.getEnd().getY()) {
			for(int x = (int) Math.min(l.getStart().getX(), l.getEnd().getX()); x < Math.max(l.getStart().getX(), l.getEnd().getX()); x++) {
				if(target.getX() == x) {
					return false;
				}
				if(isCellObstructed(x, (int)l.	getStart().getY()) && isCellObstructed(x, (int)l.getStart().getY() - 1))
					return true;
			}
		}
		// Case for any other line
		else {
			int dx = Math.abs(end.getX() - start.getX());
			int dy = Math.abs(end.getY() - start.getY());
			int x = start.getX();
			int y = start.getY();
			int n = 1 + dx + dy;
			int x_inc = (end.getX() > start.getX()) ? 1 : -1;
			int y_inc = (end.getY() > start.getY()) ? 1 : -1;
			int error = dx - dy;
			dx *= 2;
			dy *= 2;
			for(; n > 0; --n) {
				if(target.getX() == x && target.getY() == y)
					break;
				if(World.inBounds(x, y))
				if(isCellObstructed(x, y))
					return true;
				
				if(error > 0) {
					x += x_inc;
					error -=  dy;
				}
				else {
					y += y_inc;
					error += dx;
				}
			}
		}
		
		return false;
	}
	
	private static boolean isLineConcealed(Line l, Cell start, Cell end, GameObject unit, GameObject target) {
		// Case for vertical line
		if(l.getStart().getX() == l.getEnd().getX()) {
			for(int y = (int) Math.min(l.getStart().getY(), l.getEnd().getY()); y < Math.max(l.getStart().getY(), l.getEnd().getY()); y++) {
				if(target.getY() == y) {
					return false;
				}
				if(isCellObstructed((int)l.getStart().getX(), y) && isCellObstructed((int)l.getStart().getX() - 1, y))
					return true;
			}
		}
		// Case for horizontal line
		else if(l.getStart().getY() == l.getEnd().getY()) {
			for(int x = (int) Math.min(l.getStart().getX(), l.getEnd().getX()); x < Math.max(l.getStart().getX(), l.getEnd().getX()); x++) {
				if(target.getX() == x) {
					return false;
				}
				if(isCellObstructed(x, (int)l.getStart().getY()) && isCellObstructed(x, (int)l.getStart().getY() - 1))
					return true;
			}
		}
		// Case for any other line
		else {
			int dx = Math.abs(end.getX() - start.getX());
			int dy = Math.abs(end.getY() - start.getY());
			int x = start.getX();
			int y = start.getY();
			int n = 1 + dx + dy;
			int x_inc = (end.getX() > start.getX()) ? 1 : -1;
			int y_inc = (end.getY() > start.getY()) ? 1 : -1;
			int error = dx - dy;
			dx *= 2;
			dy *= 2;
			for(; n > 0; --n) {
				if(target.getX() == x && target.getY() == y)
					break;
				if(World.inBounds(x, y))
				if(isCellConcealed(x, y))
					return true;
				
				if(error > 0) {
					x += x_inc;
					error -=  dy;
				}
				else {
					y += y_inc;
					error += dx;
				}
			}
		}
		
		return false;
	}
	
	static public ShotData calcShot(Unit attacker, GameObject target) {
		Point p = null;
		float best = 0;
		
		Point[] attackerCorners = getUnitCorners(attacker);
		Point[] targetCorners = getUnitCorners(target);
		
		for(Point p0 : attackerCorners) {
			
			int cover = 0;
			int conceal = 0;
			
			for(Point p1 : targetCorners) {
				Line l = new Line(p0.getX(), p0.getY(), p1.getX(), p1.getY());
				
				if(!isLineObstructed(l, attacker.getCell(), target.getCell(), attacker, target))
					cover++;
				
				if(!isLineConcealed(l, attacker.getCell(), target.getCell(), attacker, target))
					conceal++;
			}
			
			float t = getCoverPenalty(cover) + getConcealPenalty(conceal);
			
			if(t > best) {
				best = t;
				p = p0;
			}
		}
		
		if(p != null)	return new ShotData(new objects.Point((int)p.getX(), (int)p.getY()), best);
		else	return new ShotData(null, best);
	}
}
