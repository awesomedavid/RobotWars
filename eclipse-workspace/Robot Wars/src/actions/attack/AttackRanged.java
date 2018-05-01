package actions.attack;

import objects.GameObject;
import objects.Unit;

public abstract class AttackRanged extends Attack
{	
	public AttackRanged(Unit actor, GameObject target, int aimTime, int shotSpeed, float accuracyPenalty) {
		super(actor, target, aimTime, shotSpeed, accuracyPenalty);
	}
	
}
