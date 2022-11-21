package abilities.minion_abilities;

import fileio.CardInput;

/**
 * Class Miraj extends class MinionAbility and implements
 * the useAbility method, according to the card's unique ability.
 */
public final class Miraj extends MinionAbility {
    public Miraj(final CardInput attacker, final CardInput attacked) {
        super(attacker, attacked);
    }

    /**
     * Swap his and the attacked card's health values.
     */
    @Override
    public void useAbility() {
        int healthAttacker = attacker.getHealth();
        int healthAttacked = attacked.getHealth();
        attacker.setHealth(healthAttacked);
        attacked.setHealth(healthAttacker);
    }
}
