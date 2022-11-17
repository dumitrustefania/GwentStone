package play.abilities.environment_abilities;

import fileio.CardInput;
import play.Match;
import util.JSONout;

import java.util.ArrayList;

/**
 *
 */
public final class Winterfell extends EnvironmentAbility {
    public Winterfell(final ArrayList<CardInput> affectedRow, final Match match,
                      final CardInput envCard, final JSONout out) {
        super(affectedRow, match, envCard, out);
    }

    //comm
    @Override
    public void useAbility() {
        for (CardInput card : affectedRow) {
            if (!match.getFrozenCards().contains(card)) {
                match.getFrozenCards().add(card);
            }
        }

        match.getCurrentPlayer().getHand().remove(envCard);
        match.getCurrentPlayer().setMana(match.getCurrentPlayer().getMana() - envCard.getMana());
    }
}
