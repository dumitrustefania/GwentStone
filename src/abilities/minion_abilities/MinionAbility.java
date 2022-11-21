package abilities.minion_abilities;

import fileio.CardInput;
import abilities.Ability;

/**
 * Abstract class MinionAbility implements Ability class.
 * It contains the fields necessary for a minion
 * to perform its ability.
 */
public abstract class MinionAbility implements Ability {
    protected CardInput attacker, attacked;

    public MinionAbility(final CardInput attacker, final CardInput attacked) {
        this.attacker = attacker;
        this.attacked = attacked;
    }

    /**
     * Use hero's ability.
     */
    public abstract void useAbility();
}
