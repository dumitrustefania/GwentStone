package play.action.environment_abilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.CardInput;
import play.Game;
import play.action.Ability;
import util.JSONout;

import java.util.ArrayList;

public abstract class EnvironmentAbility implements Ability{
        protected ArrayList<CardInput> affectedRow;
        protected Game game;
        protected CardInput envCard;
        protected JSONout out;

        public EnvironmentAbility(ArrayList<CardInput> affectedRow, Game game, CardInput envCard, JSONout out) {
            this.affectedRow = affectedRow;
            this.game = game;
            this.envCard = envCard;
            this.out = out;
        }
        public abstract void useAbility() throws JsonProcessingException;
}
