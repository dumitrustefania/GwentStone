package play.abilities.hero_abilities;

import fileio.CardInput;
import play.Match;

import java.util.ArrayList;

/**
 *
 */
public final class LordRoyce extends HeroAbility {
    public LordRoyce(final ArrayList<CardInput> affectedRow, final Match match) {
        super(affectedRow, match);
    }

    @Override
    public void useAbility() {
        CardInput selectedCard = null;
        int maxDamage = 0;
        for (CardInput card : affectedRow) {
            if (card.getAttackDamage() >= maxDamage) {
                maxDamage = card.getAttackDamage();
                selectedCard = card;
            }
        }

        match.getFrozenCards().add(selectedCard);
    }
}

