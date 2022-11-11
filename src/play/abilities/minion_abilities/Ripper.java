package play.abilities.minion_abilities;

import fileio.CardInput;

public class Ripper extends MinionAbility {
    public Ripper(CardInput attacker, CardInput attacked) {
        super(attacker, attacked);
    }

    @Override
    public void useAbility() {
        attacked.setAttackDamage(Math.max(0, attacked.getAttackDamage() - 2));
    }
}

