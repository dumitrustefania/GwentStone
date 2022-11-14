package play.abilities.hero_abilities;

import fileio.CardInput;
import play.Game;

import java.util.ArrayList;

/**
 *
 */
public abstract class HeroAbility {
    protected ArrayList<CardInput> affectedRow;
    protected Game game;

    public HeroAbility(final ArrayList<CardInput> affectedRow, final Game game) {
        this.affectedRow = affectedRow;
        this.game = game;
    }

    /**
     * comm
     */
    public abstract void useAbility();
}
