package play.abilities.environment_abilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.CardInput;
import play.Game;
import util.JSONout;

import java.util.ArrayList;

/**
 *
 */
public final class HeartHound extends EnvironmentAbility {
    public HeartHound(final ArrayList<CardInput> affectedRow, final Game game,
                      final CardInput envCard, final JSONout out) {
        super(affectedRow, game, envCard, out);
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
        int rowIdx = game.getCurrentPlayer().getRowsAssigned().get(placedOnFrontOrBack);
        ArrayList<CardInput> rowToBePlacedOn =  game.getTable().getTable().get(rowIdx);

        if (rowToBePlacedOn.size() == Constants.MAX_COLS) {
            out.setError("Cannot steal enemy card since the player's row is full.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        affectedRow.remove(stolenCard);
        rowToBePlacedOn.add(stolenCard);
        game.getCurrentPlayer().getHand().remove(envCard);
        game.getCurrentPlayer().setMana(game.getCurrentPlayer().getMana() - envCard.getMana());
    }
}
