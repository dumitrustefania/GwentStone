package play.abilities.environment_abilities;

import fileio.CardInput;
import play.Match;
import util.JSONout;

import java.util.ArrayList;

/**
 *
 */
public final class Firestorm extends EnvironmentAbility {
    public Firestorm(final ArrayList<CardInput> affectedRow, final Match match,
                     final CardInput envCard, final JSONout out) {
        super(affectedRow, match, envCard, out);
    }

    // comm
    @Override
    public void useAbility() {
        for (int i = affectedRow.size() - 1; i >= 0; i--) {
            CardInput card = affectedRow.get(i);
            card.setHealth(card.getHealth() - 1);
            if (card.getHealth() <= 0) {
                affectedRow.remove(card);
                match.getFrozenCards().remove(card);
            }

        }

        match.getCurrentPlayer().getHand().remove(envCard);
        match.getCurrentPlayer().setMana(match.getCurrentPlayer().getMana() - envCard.getMana());
    }
}
