package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.ActionsInput;
import fileio.CardInput;
import play.Match;

/**
 * Action class is the first to process each action and redirect it
 * based on its command name.
 */
public class Action {
    protected ActionsInput action;
    protected Match match;

    public Action(final ActionsInput action, final Match match) {
        this.action = action;
        this.match = match;
    }

    /**
     * Check the command name of the action and create the necessary
     * object for executing it.
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
     * Return true if input card already attacked this turn.
     * @param card
     * @return
     */
    public boolean attackedThisTurn(final CardInput card) {
        return match.getAttackedThisTurn().contains(card);
    }
}
