package play.abilities.minion_abilities;

import fileio.CardInput;

/**
 *
 */
public final class Miraj extends MinionAbility {
    public Miraj(final CardInput attacker, final CardInput attacked) {
        super(attacker, attacked);
    }

    @Override
    public void useAbility() {
        int healthAttacker = attacker.getHealth();
        int healthAttacked = attacked.getHealth();
        attacker.setHealth(healthAttacked);
        attacked.setHealth(healthAttacker);
    }
}
