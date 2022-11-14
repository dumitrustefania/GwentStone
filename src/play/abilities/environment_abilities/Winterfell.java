package play.abilities.environment_abilities;

import fileio.CardInput;
import play.Game;
import util.JSONout;

import java.util.ArrayList;

/**
 *
 */
public final class Winterfell extends EnvironmentAbility {
    public Winterfell(final ArrayList<CardInput> affectedRow, final Game game,
                      final CardInput envCard, final JSONout out) {
        super(affectedRow, game, envCard, out);
    }

    //comm
    @Override
    public void useAbility() {
        for (CardInput card : affectedRow) {
            if (!game.getFrozenCards().contains(card)) {
                game.getFrozenCards().add(card);
            }
        }

        game.getCurrentPlayer().getHand().remove(envCard);
        game.getCurrentPlayer().setMana(game.getCurrentPlayer().getMana() - envCard.getMana());
    }
}
