package actions.attack;

import animations.AnimBeamBurst;
import animations.AnimBeamBurstLaser;
import animations.AnimProjectileMachineGun;
import animations.text.AnimFloatTextAttack;
import core.DamageType;
import core.Game;
import effects.Damage;
import objects.GameObject;
import objects.Unit;

public class AttackMachineGun extends AttackRanged
{
	public AttackMachineGun(Unit actor, GameObject target, float accuracyPenalty) {
		super(actor, target, WEAPON_MG_AIM_TIME, WEAPON_MG_ANIMATION_SPEED, accuracyPenalty);
	}
	
	protected void complete()
	{
		super.complete();
		
		if(hit())
		{
			effect(new Damage(actor, target, calculateDelay(), WEAPON_MG_DAMAGE, WEAPON_MG_TYPE));
		}
		
		animation(new AnimProjectileMachineGun(actor.getPointPixel(), actor, target, hit()));
	}
	


}
