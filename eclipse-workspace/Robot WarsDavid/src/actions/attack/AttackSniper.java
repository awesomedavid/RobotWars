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

public class AttackSniper extends AttackProjectile
{
	
	public AttackSniper(Unit actor, GameObject target, float accuracyPenalty) {
		super(actor, target, WEAPON_SNIPER_AIM_TIME, WEAPON_SNIPER_ANIMATION_SPEED, accuracyPenalty);
		damage = WEAPON_SNIPER_DAMAGE;
	}
	
	protected void complete()
	{
		super.complete();
		
		if(hit())
		{
			effect(new Damage(actor, target, calculateDelay(), damage, WEAPON_SNIPER_TYPE));
		}
		
		animation(new AnimProjectileSniper(actor.getPointPixel(), actor, target, hit()));
	}
}
