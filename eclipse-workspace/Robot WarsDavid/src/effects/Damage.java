package effects;

import core.DamageType;
import objects.GameObject;
import objects.Unit;

public class Damage extends Effect 
{
	float damageInitial;
	DamageType type;
	
	public Damage(Unit source, GameObject target, int delay, float damageInitial, DamageType type) {
		super(source, target);
		hasInitial = true;
		this.delay = delay;
		this.damageInitial = damageInitial;
		this.duration = delay + 1;
		this.type = type;
	}


	@Override
	public void initialEffect() {
		target.takeDamage(damageInitial, type, source);
		
	}

	@Override
	public void periodicEffect() {
		// TODO Auto-generated method stub
		
	}
}
