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

public class AttackLaser extends AttackRanged
{
	
	public AttackLaser(Unit actor, GameObject target, float accuracyPenalty) {
		super(actor, target, WEAPON_LASER_AIM_TIME, 0, accuracyPenalty);
		damage = WEAPON_LASER_DAMAGE;
	}
	
	protected void complete()
	{
		super.complete();
		
		if(hit())
		{
			effect(new Damage(actor, target, 1, damage, WEAPON_LASER_TYPE));
		}

		animation(new AnimBeamBurstLaser(actor.getPointPixel(), actor, target, hit()));
	}
}
