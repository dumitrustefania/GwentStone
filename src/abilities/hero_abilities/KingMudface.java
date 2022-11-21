package abilities.hero_abilities;

import fileio.CardInput;
import game.Match;

import java.util.ArrayList;

/**
 * Class KingMudface extends class HeroAbility and implements
 * the useAbility method, according to the card's unique ability.
 */
public final class KingMudface extends HeroAbility {
    public KingMudface(final ArrayList<CardInput> affectedRow, final Match match) {
        super(affectedRow, match);
    }

    /**
     * Increase(+1) the health value of all cards on row.
     */
    @Override
    public void useAbility() {
        for (CardInput card : affectedRow) {
            card.setHealth(card.getHealth() + 1);
        }
    }
}
