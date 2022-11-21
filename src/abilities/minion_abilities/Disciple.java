package abilities.minion_abilities;

import fileio.CardInput;

/**
 * Class Disciple extends class MinionAbility and implements
 * the useAbility method, according to the card's unique ability.
 */
public final class Disciple extends MinionAbility {
    public Disciple(final CardInput attacker, final CardInput attacked) {
        super(attacker, attacked);
    }

    /**
     * Increase(+2) the attacked card's health value.
     */
    @Override
    public void useAbility() {
        attacked.setHealth(attacked.getHealth() + 2);
    }
}
