package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.CardInput;
import play.players.Player;
import util.JSONout;

import java.util.ArrayList;

/**
 *
 */
public final class DebugAction extends Action {
    public DebugAction(final Action action) {
        super(action.action, action.match);
    }

    /**
     * @throws JsonProcessingException
     */
    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();
        JSONout out = new JSONout();
        out.setCommand(command);

        if (command.equals(Constants.GET_DECK)) {
            out.setPlayerIdx(action.getPlayerIdx());
            out.setOutput(match.getPlayers()[action.getPlayerIdx()].getCurrentDeck());
            out.appendToArrayNode(match.getOutput());
        }

        if (command.equals(Constants.GET_HERO)) {
            out.setPlayerIdx(action.getPlayerIdx());
            out.setOutput(match.getPlayers()[action.getPlayerIdx()].getHero());
            out.appendToArrayNode(match.getOutput());
        }

        if (command.equals(Constants.GET_TURN)) {
            out.setOutput(match.getCurrentPlayer().getPlayerNum());
            out.appendToArrayNode(match.getOutput());
        }

        if (command.equals(Constants.GET_HAND)) {
            out.setPlayerIdx(action.getPlayerIdx());
            out.setOutput(match.getPlayers()[action.getPlayerIdx()].getHand());
            out.appendToArrayNode(match.getOutput());
        }

        if (command.equals(Constants.GET_MANA)) {
            out.setPlayerIdx(action.getPlayerIdx());
            out.setOutput(match.getPlayers()[action.getPlayerIdx()].getMana());
            out.appendToArrayNode(match.getOutput());
        }

        if (command.equals(Constants.GET_TABLE)) {
            out.setOutput(match.getTable().getTable());
            out.appendToArrayNode(match.getOutput());
        }

        if (command.equals(Constants.GET_GAMES)) {
            out.setOutput(match.getGamesPlayed());
            out.appendToArrayNode(match.getOutput());
        }

        if (command.equals(Constants.GET_CARD)) {
            out.setOutput(match.getTable().getCard(action.getX(), action.getY()));
            out.appendToArrayNode(match.getOutput());
        }

        if (command.equals(Constants.GET_ENV)) {
            out.setPlayerIdx(action.getPlayerIdx());

            Player player = match.getPlayers()[action.getPlayerIdx()];
            ArrayList<CardInput> envCards = new ArrayList<CardInput>();

            for (CardInput card : player.getHand()) {
                if (Constants.ENV_CARDS.contains(card.getName())) {
                    envCards.add(card);
                }
            }

            out.setOutput(envCards);
            out.appendToArrayNode(match.getOutput());
        }

        if (command.equals(Constants.GET_FROZEN)) {
            out.setOutput(match.getFrozenCards());
            out.appendToArrayNode(match.getOutput());
        }

        if (command.equals(Constants.GET_WINS_ONE)) {
            out.setOutput(match.getPlayers()[1].getWins());
            out.appendToArrayNode(match.getOutput());
        }

        if (command.equals(Constants.GET_WINS_TWO)) {
            out.setOutput(match.getPlayers()[2].getWins());
            out.appendToArrayNode(match.getOutput());
        }
    }
}
