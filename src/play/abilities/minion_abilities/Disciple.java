package play.abilities.minion_abilities;

import fileio.CardInput;

/**
 *
 */
public final class Disciple extends MinionAbility {
    public Disciple(final CardInput attacker, final CardInput attacked) {
        super(attacker, attacked);
    }

    @Override
    public void useAbility() {
        attacked.setHealth(attacked.getHealth() + 2);
    }
}
