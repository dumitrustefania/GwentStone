package play.abilities.minion_abilities;

import fileio.CardInput;

public class Disciple extends MinionAbility{
    public Disciple(CardInput attacker, CardInput attacked) {
        super(attacker, attacked);
    }

    @Override
    public void useAbility() {
        attacked.setHealth(attacked.getHealth() + 2);
    }
}
