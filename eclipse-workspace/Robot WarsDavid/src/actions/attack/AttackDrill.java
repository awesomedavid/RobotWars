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

public class AttackDrill extends AttackMelee
{
	public AttackDrill(Unit actor, GameObject target) {
		super(actor, target, WEAPON_DRILL_AIM_TIME);
	}
	
	protected void complete()
	{
		super.complete();
		
		if(hit())
		{
			effect(new Damage(actor, target, 1, WEAPON_DRILL_DAMAGE, WEAPON_DRILL_TYPE));
		}
		
		//animation(new AnimProjectileMachineGun(actor.getPointPixel(), actor, target, hit()));
	}
	


}
