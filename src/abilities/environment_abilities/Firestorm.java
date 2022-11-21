package abilities.environment_abilities;

import fileio.CardInput;
import game.Match;
import util.JsonOut;

import java.util.ArrayList;

/**
 * Class Firestorm extends class EnvironmentAbility and implements
 * the useAbility method, according to the card's unique ability.
 */
public final class Firestorm extends EnvironmentAbility {
    public Firestorm(final ArrayList<CardInput> affectedRow, final Match match,
                     final CardInput envCard, final JsonOut out) {
        super(affectedRow, match, envCard, out);
    }

    /**
     * Decrease(-1) the health property of all cards on row.
     */
    @Override
    public void useAbility() {
        for (int i = affectedRow.size() - 1; i >= 0; i--) {
            CardInput card = affectedRow.get(i);
            card.setHealth(card.getHealth() - 1);

            // check whether card is dead
            if (card.getHealth() <= 0) {
                // remove card from table
                affectedRow.remove(card);
            }

        }

        // remove environment card from the player's hand and decrease its mana
        match.getCurrentPlayer().getHand().remove(envCard);
        match.getCurrentPlayer().setMana(match.getCurrentPlayer().getMana() - envCard.getMana());
    }
}
