package abilities.hero_abilities;

import fileio.CardInput;
import game.Match;

import java.util.ArrayList;

/**
 * Class GeneralKocioraw extends class HeroAbility and implements
 * the useAbility method, according to the card's unique ability.
 */
public final class GeneralKocioraw extends HeroAbility {
    public GeneralKocioraw(final ArrayList<CardInput> affectedRow, final Match match) {
        super(affectedRow, match);
    }

    /**
     * Increase(+1) the attack damage value of all cards on row.
     */
    @Override
    public void useAbility() {
        for (CardInput card : affectedRow) {
            card.setAttackDamage(card.getAttackDamage() + 1);
        }
    }
}
