package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.ActionsInput;
import fileio.CardInput;
import play.Match;

/**
 *
 */
public class Action {
    protected ActionsInput action;
    protected Match match;

    public Action(final ActionsInput action, final Match match) {
        this.action = action;
        this.match = match;
    }

    public Action(final Match match) {
        this.match = match;
    }

    public Action() {
    }

    /**
     * @throws JsonProcessingException
     */
    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();

        if (Constants.DEBUG_ACTIONS.contains(command)) {
            new DebugAction(this).performAction();
        }

        if (Constants.MINION_ACTIONS.contains(command)) {
            new MinionAction(this).performAction();
        }

        if (Constants.PLAYER_ACTIONS.contains(command)) {
            new PlayerAction(this).performAction();
        }

        if (Constants.HERO_ACTIONS.contains(command)) {
            new HeroAction(this).performAction();
        }

        if (Constants.ENV_ACTIONS.contains(command)) {
            new EnvironmentAction(this).performAction();
        }
    }

    /**
     * @param card
     * @return
     */
    public boolean attackedThisTurn(final CardInput card) {
        return match.getAttackedThisTurn().contains(card);
    }
}
