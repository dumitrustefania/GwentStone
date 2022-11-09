package play.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import common.Constants;
import fileio.ActionsInput;
import fileio.DecksInput;
import play.Game;
import util.JSONout;

public class DebugAction extends Action{
    public DebugAction(Action action) {
        super(action.action, action.game);
    }

    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();

        if(command.equals(Constants.GET_DECK)) {
            JSONout out = new JSONout();
            out.setCommand(command);
            out.setOutput(game.getPlayers()[action.getPlayerIdx()].getCurrentDeck());
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_HERO)) {
            JSONout out = new JSONout();
            out.setCommand(command);
            out.setOutput(game.getCurrentPlayer().getHero());
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_TURN)) {
            JSONout out = new JSONout();
            out.setCommand(command);
            out.setOutput(game.getCurrentPlayer().getPlayerNum());
            out.appendToArrayNode(game.getOutput());
        }


    }
}
