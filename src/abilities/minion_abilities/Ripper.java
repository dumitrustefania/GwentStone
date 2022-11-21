package abilities.minion_abilities;

import fileio.CardInput;

/**
 * Class Ripper extends class MinionAbility and implements
 * the useAbility method, according to the card's unique ability.
 */
public final class Ripper extends MinionAbility {
    public Ripper(final CardInput attacker, final CardInput attacked) {
        super(attacker, attacked);
    }

    /**
     * Decrease(-2) the attacked card's attack damage value.
     */
    @Override
    public void useAbility() {
        attacked.setAttackDamage(Math.max(0, attacked.getAttackDamage() - 2));
    }
}
