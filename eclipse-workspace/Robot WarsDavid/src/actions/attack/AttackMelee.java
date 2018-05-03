package actions.attack;

import objects.GameObject;
import objects.Unit;

public abstract class AttackMelee extends Attack
{	
	public AttackMelee(Unit actor, GameObject target, int aimTime) {
		super(actor, target, aimTime, 0, 0);
	}
	
}
