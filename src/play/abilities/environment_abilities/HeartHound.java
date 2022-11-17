package play.abilities.environment_abilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.CardInput;
import play.Match;
import util.JSONout;

import java.util.ArrayList;

/**
 *
 */
public final class HeartHound extends EnvironmentAbility {
    public HeartHound(final ArrayList<CardInput> affectedRow, final Match match,
                      final CardInput envCard, final JSONout out) {
        super(affectedRow, match, envCard, out);
    }

    //comm
    @Override
    public void useAbility() throws JsonProcessingException {
        CardInput stolenCard = null;
        int maxHealth = 0;
        for (CardInput card : affectedRow) {
            if (card.getHealth() > maxHealth) {
                maxHealth = card.getHealth();
                stolenCard = card;
            }
        }

        int placedOnFrontOrBack = Constants.ROW_TO_BE_PLACED_ON.get(stolenCard.getName());
        int rowIdx = match.getCurrentPlayer().getRowsAssigned().get(placedOnFrontOrBack);
        ArrayList<CardInput> rowToBePlacedOn =  match.getTable().getTable().get(rowIdx);

        if (rowToBePlacedOn.size() == Constants.MAX_COLS) {
            out.setError("Cannot steal enemy card since the player's row is full.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        affectedRow.remove(stolenCard);
        rowToBePlacedOn.add(stolenCard);
        match.getCurrentPlayer().getHand().remove(envCard);
        match.getCurrentPlayer().setMana(match.getCurrentPlayer().getMana() - envCard.getMana());
    }
}
