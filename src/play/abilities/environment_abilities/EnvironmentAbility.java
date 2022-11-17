package play.abilities.environment_abilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.CardInput;
import play.Match;
import play.abilities.Ability;
import util.JSONout;

import java.util.ArrayList;

/**
 *
 */
public abstract class EnvironmentAbility implements Ability {
    protected ArrayList<CardInput> affectedRow;
    protected Match match;
    protected CardInput envCard;
    protected JSONout out;

    public EnvironmentAbility(final ArrayList<CardInput> affectedRow, final Match match,
                              final CardInput envCard, final JSONout out) {
        this.affectedRow = affectedRow;
        this.match = match;
        this.envCard = envCard;
        this.out = out;
    }

    /**
     * add comm
     * @throws JsonProcessingException
     */
    public abstract void useAbility() throws JsonProcessingException;
}
