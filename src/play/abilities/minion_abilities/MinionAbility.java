package play.abilities.minion_abilities;

import fileio.CardInput;
import play.abilities.Ability;

/**
 *
 */
public abstract class MinionAbility implements Ability {
    protected CardInput attacker, attacked;

    public MinionAbility(final CardInput attacker, final CardInput attacked) {
        this.attacker = attacker;
        this.attacked = attacked;
    }

    /**
     * com
     * */
    public abstract void useAbility();
}
