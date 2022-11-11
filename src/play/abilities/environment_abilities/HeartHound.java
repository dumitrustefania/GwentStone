package play.abilities.environment_abilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.Constants;
import fileio.CardInput;
import play.Game;
import play.actions.Action;
import util.JSONout;

import java.util.ArrayList;

public class HeartHound extends EnvironmentAbility{
    public HeartHound(ArrayList<CardInput> affectedRow, Game game, CardInput envCard, JSONout out) {
        super(affectedRow, game, envCard, out);
    }

    @Override
    public void useAbility() throws JsonProcessingException {
        CardInput stolenCard = null;
        int maxHealth = 0;
        for(CardInput card: affectedRow)
            if(card.getHealth() > maxHealth) {
                maxHealth = card.getHealth();
                stolenCard = card;
            }

        int placedOnFrontOrBack = Constants.ROW_TO_BE_PLACED_ON.get(stolenCard.getName());
        ArrayList<CardInput> rowToBePlacedOn = new Action(game).getRow(placedOnFrontOrBack, game.getCurrentPlayer());

        if(rowToBePlacedOn.size() == 5) {
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
