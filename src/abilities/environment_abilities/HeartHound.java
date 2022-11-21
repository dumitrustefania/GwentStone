package abilities.environment_abilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.CardInput;
import game.Match;
import util.JsonOut;

import java.util.ArrayList;

/**
 * Class HeartHound extends class EnvironmentAbility and implements
 * the useAbility method, according to the card's unique ability.
 */
public final class HeartHound extends EnvironmentAbility {
    public HeartHound(final ArrayList<CardInput> affectedRow, final Match match,
                      final CardInput envCard, final JsonOut out) {
        super(affectedRow, match, envCard, out);
    }

    /**
     * Steal the opponent's card that has the highest health value.
     * @throws JsonProcessingException
     */
    @Override
    public void useAbility() throws JsonProcessingException {
        // find card on row with the highest health value
        CardInput stolenCard = null;
        int maxHealth = 0;
        for (CardInput card : affectedRow) {
            if (card.getHealth() > maxHealth) {
                maxHealth = card.getHealth();
                stolenCard = card;
            }
        }

        // determine current player's row where the card should be placed
        int placedOnFrontOrBack = Constants.ROW_TO_BE_PLACED_ON.get(stolenCard.getName());
        int rowIdx = match.getCurrentPlayer().getRowsAssigned().get(placedOnFrontOrBack);
        ArrayList<CardInput> rowToBePlacedOn =  match.getTable().getTable().get(rowIdx);

        // check whether the row is already full
        if (rowToBePlacedOn.size() == Constants.MAX_COLS) {
            out.setError("Cannot steal enemy card since the player's row is full.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        // steal the card
        affectedRow.remove(stolenCard);
        rowToBePlacedOn.add(stolenCard);

        // remove environment card from the player's hand and decrease its mana
        match.getCurrentPlayer().getHand().remove(envCard);
        match.getCurrentPlayer().setMana(match.getCurrentPlayer().getMana() - envCard.getMana());
    }
}
