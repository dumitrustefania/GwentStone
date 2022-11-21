package abilities.hero_abilities;

import fileio.CardInput;
import game.Match;

import java.util.ArrayList;

/**
 * Class LordRoyce extends class HeroAbility and implements
 * the useAbility method, according to the card's unique ability.
 */
public final class LordRoyce extends HeroAbility {
    public LordRoyce(final ArrayList<CardInput> affectedRow, final Match match) {
        super(affectedRow, match);
    }

    /**
     * Freeze the card with the highest attack damage value on row.
     */
    @Override
    public void useAbility() {
        // find card with the highest attack damage value
        CardInput selectedCard = null;
        int maxDamage = 0;
        for (CardInput card : affectedRow) {
            if (card.getAttackDamage() >= maxDamage) {
                maxDamage = card.getAttackDamage();
                selectedCard = card;
            }
        }

        // freeze the card
        match.getFrozenCards().add(selectedCard);
    }
}

