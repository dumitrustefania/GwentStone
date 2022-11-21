package actions;

import util.Constants;
import fileio.CardInput;
import players.Player;
import util.JsonOut;

import java.util.ArrayList;
import java.util.Objects;

/**
 * DebugAction class is used for performing debug actions given in the input.
 */
public final class DebugAction extends Action {
    public DebugAction(final Action action) {
        super(action.action, action.match);
    }

    /**
     * Check the command name and execute the necessary steps accordingly.
     */
    public void performAction() {
        String command = action.getCommand();
        JsonOut out = new JsonOut();
        out.setCommand(command);

        if (command.equals(Constants.GET_DECK)) {
            out.setPlayerIdx(action.getPlayerIdx());
            // output = the current deck of the required player
            out.setOutput(match.getPlayers()[action.getPlayerIdx()].getCurrentDeck());
        }

        if (command.equals(Constants.GET_HERO)) {
            out.setPlayerIdx(action.getPlayerIdx());
            // output = the current hero of the required player
            out.setOutput(match.getPlayers()[action.getPlayerIdx()].getHero());
        }

        if (command.equals(Constants.GET_TURN)) {
            // output = the number of the player whose turn is now
            out.setOutput(match.getCurrentPlayer().getPlayerNum());
        }

        if (command.equals(Constants.GET_HAND)) {
            out.setPlayerIdx(action.getPlayerIdx());
            // output = the current hand of the required player
            out.setOutput(match.getPlayers()[action.getPlayerIdx()].getHand());
        }

        if (command.equals(Constants.GET_MANA)) {
            out.setPlayerIdx(action.getPlayerIdx());
            // output = the current mana of the required player
            out.setOutput(match.getPlayers()[action.getPlayerIdx()].getMana());
        }

        if (command.equals(Constants.GET_TABLE)) {
            // output = all the cards currently present on the table
            out.setOutput(match.getTable().getTable());
        }

        if (command.equals(Constants.GET_GAMES)) {
            // output = the number of matches played
            out.setOutput(match.getMatchesPlayed());
        }

        if (command.equals(Constants.GET_CARD)) {
            out.setX(action.getX());
            out.setY(action.getY());
            // output = the card present on the table on row X and col Y
            CardInput card = match.getTable().getCard(action.getX(), action.getY());
            out.setOutput(Objects.requireNonNullElse(card, "No card available at that position."));
        }

        if (command.equals(Constants.GET_ENV)) {
            out.setPlayerIdx(action.getPlayerIdx());

            Player player = match.getPlayers()[action.getPlayerIdx()];
            ArrayList<CardInput> envCards = new ArrayList<CardInput>();

            // traverse the requested player's hand and save its
            // environment card inside an ArrayList
            for (CardInput card : player.getHand()) {
                if (Constants.ENV_CARDS.contains(card.getName())) {
                    envCards.add(card);
                }
            }

            // output = the environment cards from the required player's hand
            out.setOutput(envCards);
        }

        if (command.equals(Constants.GET_FROZEN)) {
            // output = all the cards on the table that are currently frozen
            out.setOutput(match.getFrozenCards());
        }

        if (command.equals(Constants.GET_WINS_ONE)) {
            // output = the no of wins of the player 1
            out.setOutput(match.getPlayers()[1].getWins());
        }

        if (command.equals(Constants.GET_WINS_TWO)) {
            // output = the no of wins of the player 2
            out.setOutput(match.getPlayers()[2].getWins());
        }

        out.appendToArrayNode(match.getOutput());

    }
}
