package play.abilities.minion_abilities;

import fileio.CardInput;

/**
 *
 */
public final class Cursed extends MinionAbility {
    public Cursed(final CardInput attacker, final CardInput attacked) {
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
