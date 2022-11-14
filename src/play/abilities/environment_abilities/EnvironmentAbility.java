package play.abilities.environment_abilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.CardInput;
import play.Game;
import play.abilities.Ability;
import util.JSONout;

import java.util.ArrayList;

/**
 *
 */
public abstract class EnvironmentAbility implements Ability {
    protected ArrayList<CardInput> affectedRow;
    protected Game game;
    protected CardInput envCard;
    protected JSONout out;

    public EnvironmentAbility(final ArrayList<CardInput> affectedRow, final Game game,
                              final CardInput envCard, final JSONout out) {
        this.affectedRow = affectedRow;
        this.game = game;
        this.envCard = envCard;
        this.out = out;
    }

    /**
     * add comm
     * @throws JsonProcessingException
     */
    public abstract void useAbility() throws JsonProcessingException;
}
