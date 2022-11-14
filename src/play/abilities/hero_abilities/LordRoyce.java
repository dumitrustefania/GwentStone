package play.abilities.hero_abilities;

import fileio.CardInput;
import play.Game;

import java.util.ArrayList;

/**
 *
 */
public final class LordRoyce extends HeroAbility {
    public LordRoyce(final ArrayList<CardInput> affectedRow, final Game game) {
        super(affectedRow, game);
    }

    @Override
    public void useAbility() {
        System.out.println(affectedRow);
        CardInput selectedCard = null;
        int maxDamage = 0;
        for (CardInput card : affectedRow) {
            if (card.getAttackDamage() >= maxDamage) {
                maxDamage = card.getAttackDamage();
                selectedCard = card;
            }
        }

        game.getFrozenCards().add(selectedCard);
    }
}

