package play.action.minion_abilities;

import fileio.CardInput;
import play.action.Ability;

public abstract class MinionAbility implements Ability {
    protected CardInput attacker, attacked;

    public MinionAbility(CardInput attacker, CardInput attacked) {
        this.attacker = attacker;
        this.attacked = attacked;
    }
    public abstract void useAbility();
}
