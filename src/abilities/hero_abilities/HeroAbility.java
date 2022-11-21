package abilities.hero_abilities;

import fileio.CardInput;
import game.Match;

import java.util.ArrayList;

/**
 * Abstract class HeroAbility implements Ability class.
 * It contains the fields necessary for a hero
 * to perform its ability.
 */
public abstract class HeroAbility {
    protected ArrayList<CardInput> affectedRow;
    protected Match match;

    public HeroAbility(final ArrayList<CardInput> affectedRow, final Match match) {
        this.affectedRow = affectedRow;
        this.match = match;
    }

    /**
     * Use hero's ability.
     */
    public abstract void useAbility();
}
