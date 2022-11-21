package abilities.environment_abilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.CardInput;
import game.Match;
import abilities.Ability;
import util.JsonOut;

import java.util.ArrayList;

/**
 * Abstract class EnvironmentAbility implements Ability class.
 * It contains the fields necessary for an environment card
 * to perform its ability.
 */
public abstract class EnvironmentAbility implements Ability {
    protected ArrayList<CardInput> affectedRow;
    protected Match match;
    protected CardInput envCard;
    protected JsonOut out;

    public EnvironmentAbility(final ArrayList<CardInput> affectedRow, final Match match,
                              final CardInput envCard, final JsonOut out) {
        this.affectedRow = affectedRow;
        this.match = match;
        this.envCard = envCard;
        this.out = out;
    }

    /**
     * Use environment card's ability.
     * @throws JsonProcessingException
     */
    public abstract void useAbility() throws JsonProcessingException;
}
