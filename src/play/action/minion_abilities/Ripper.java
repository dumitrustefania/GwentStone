package play.action.minion_abilities;

import fileio.CardInput;
import play.action.Ability;

public class Ripper extends MinionAbility {
    public Ripper(CardInput attacker, CardInput attacked) {
        super(attacker, attacked);
    }

    @Override
    public void useAbility() {
        attacked.setHealth(attacked.getHealth() - 2);
    }
}

