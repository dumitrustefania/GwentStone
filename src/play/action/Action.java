package play.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.ActionsInput;
import play.Game;
import common.Constants;

public class Action {
    protected ActionsInput action;
    protected Game game;

    public Action(ActionsInput action, Game game) {
        this.action = action;
        this.game = game;
    }

    public Action() {
    }

    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();

        if (Constants.DEBUG_ACTIONS.contains(command)) {
            new DebugAction(this).performAction();
        }

        if (Constants.MINION_ACTIONS.contains(command)) {
            ((MinionAction)this).performAction();
        }
    }
}
