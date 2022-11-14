package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.ActionsInput;
import fileio.CardInput;
import play.Game;

/**
 *
 */
public class Action {
    protected ActionsInput action;
    protected Game game;

    public Action(final ActionsInput action, final Game game) {
        this.action = action;
        this.game = game;
    }

    public Action(final Game game) {
        this.game = game;
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
        return game.getAttackedThisTurn().contains(card);
    }
}
