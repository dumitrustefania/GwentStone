package play.abilities.minion_abilities;

import fileio.CardInput;

/**
 *
 */
public final class Ripper extends MinionAbility {
    public Ripper(final CardInput attacker, final CardInput attacked) {
        super(attacker, attacked);
    }

    @Override
    public void useAbility() {
        attacked.setAttackDamage(Math.max(0, attacked.getAttackDamage() - 2));
    }
}
