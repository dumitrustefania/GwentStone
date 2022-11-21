package abilities.minion_abilities;

import fileio.CardInput;

/**
 * Class Cursed extends class MinionAbility and implements
 * the useAbility method, according to the card's unique ability.
 */
public final class Cursed extends MinionAbility {
    public Cursed(final CardInput attacker, final CardInput attacked) {
        super(attacker, attacked);
    }

    /**
     * Swap the attacked card's health and attack damage values.
     */
    @Override
    public void useAbility() {
        int healthAttacked = attacked.getHealth();
        int damageAttacked = attacked.getAttackDamage();
        attacked.setHealth(damageAttacked);
        attacked.setAttackDamage(healthAttacked);
    }
}
