package actions.attack;

import animations.AnimBeamBurst;
import animations.AnimBeamBurstLaser;
import animations.AnimProjectileSniper;
import animations.text.AnimFloatText;
import animations.text.AnimFloatTextAttack;
import core.DamageType;
import core.Game;
import effects.Damage;
import objects.GameObject;
import objects.Unit;

public class AttackProjectile extends AttackRanged
{

	
	public AttackProjectile(Unit actor, GameObject target, int aimTime, int shotSpeed, float accuracyPenalty) {
		super(actor, target, aimTime, shotSpeed, accuracyPenalty);
	
		speed = shotSpeed;
	}
	
	protected void complete()
	{
		makeAttackRoll();
		
		if(!hit)
		{
			Game.addAnimation(new AnimFloatTextAttack("Miss!", target));
		}
	}
	
	public int calculateDelay()
	{
		return calculateDelay(speed);
	}
	
	
}
