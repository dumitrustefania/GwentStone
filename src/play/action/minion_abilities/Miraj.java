package play.action.minion_abilities;

import fileio.CardInput;

public class Miraj extends MinionAbility{
    public Miraj(CardInput attacker, CardInput attacked) {
        super(attacker, attacked);
    }

    @Override
    public void useAbility() {
        int healthAttacker = attacker.getHealth();
        int healthAttacked = attacked.getHealth();
        attacker.setHealth(healthAttacked);
        attacked.setHealth(healthAttacker);
    }
}
