package abilities.environment_abilities;

import fileio.CardInput;
import game.Match;
import util.JsonOut;

import java.util.ArrayList;

/**
 * Class Winterfell extends class EnvironmentAbility and implements
 * the useAbility method, according to the card's unique ability.
 */
public final class Winterfell extends EnvironmentAbility {
    public Winterfell(final ArrayList<CardInput> affectedRow, final Match match,
                      final CardInput envCard, final JsonOut out) {
        super(affectedRow, match, envCard, out);
    }

    /**
     * Freeze all cards on the row.
     */
    @Override
    public void useAbility() {
        // freeze cards
        for (CardInput card : affectedRow) {
            if (!match.getFrozenCards().contains(card)) {
                match.getFrozenCards().add(card);
            }
        }

        // remove environment card from the player's hand and decrease its mana
        match.getCurrentPlayer().getHand().remove(envCard);
        match.getCurrentPlayer().setMana(match.getCurrentPlayer().getMana() - envCard.getMana());
    }
}
