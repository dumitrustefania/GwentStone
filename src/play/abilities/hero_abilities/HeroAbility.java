package play.abilities.hero_abilities;

import fileio.CardInput;
import play.Match;

import java.util.ArrayList;

/**
 *
 */
public abstract class HeroAbility {
    protected ArrayList<CardInput> affectedRow;
    protected Match match;

    public HeroAbility(final ArrayList<CardInput> affectedRow, final Match match) {
        this.affectedRow = affectedRow;
        this.match = match;
    }

    /**
     * comm
     */
    public abstract void useAbility();
}
