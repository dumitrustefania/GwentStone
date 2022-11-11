package play.abilities.minion_abilities;

import fileio.CardInput;

public class Cursed extends MinionAbility{
    public Cursed(CardInput attacker, CardInput attacked) {
        super(attacker, attacked);
    }

    @Override
    public void useAbility() {
        int healthAttacked = attacked.getHealth();
        int damageAttacked = attacked.getAttackDamage();
        attacked.setHealth(damageAttacked);
        attacked.setAttackDamage(healthAttacked);
    }
}
