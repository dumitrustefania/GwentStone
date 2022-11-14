package play.abilities.hero_abilities;

import fileio.CardInput;
import play.Game;

import java.util.ArrayList;

/**
 *
 */
public final class KingMudface extends HeroAbility {
    public KingMudface(final ArrayList<CardInput> affectedRow, final Game game) {
        super(affectedRow, game);
    }

    @Override
    public void useAbility() {
        for (CardInput card : affectedRow) {
            card.setHealth(card.getHealth() + 1);
        }
    }
}
