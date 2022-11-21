package abilities.hero_abilities;

import fileio.CardInput;
import game.Match;

import java.util.ArrayList;

/**
 * Class EmpressThorina extends class HeroAbility and implements
 * the useAbility method, according to the card's unique ability.
 */
public final class EmpressThorina extends HeroAbility {
    public EmpressThorina(final ArrayList<CardInput> affectedRow, final Match match) {
        super(affectedRow, match);
    }

    /**
     * Kill the card that has the highest health value on the row.
     */
    @Override
    public void useAbility() {
        // find the card with the highest health value
        CardInput selectedCard = null;
        int maxHealth = 0;
        for (CardInput card : affectedRow) {
            if (card.getAttackDamage() >= maxHealth) {
                maxHealth = card.getAttackDamage();
                selectedCard = card;
            }
        }

        // kill the card by removing it from the table
        affectedRow.remove(selectedCard);
    }
}
