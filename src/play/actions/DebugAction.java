package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.Constants;
import fileio.CardInput;
import play.players.Player;
import util.JSONout;

import java.util.ArrayList;

public class DebugAction extends Action{
    public DebugAction(Action action) {
        super(action.action, action.game);
    }

    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();
        JSONout out = new JSONout();
        out.setCommand(command);

        if(command.equals(Constants.GET_DECK)) {
            out.setPlayerIdx(action.getPlayerIdx());
            out.setOutput(game.getPlayers()[action.getPlayerIdx()].getCurrentDeck());
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_HERO)) {
            out.setPlayerIdx(action.getPlayerIdx());
            out.setOutput(game.getPlayers()[action.getPlayerIdx()].getHero());
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_TURN)) {
            out.setOutput(game.getCurrentPlayer().getPlayerNum());
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_HAND)) {
            out.setPlayerIdx(action.getPlayerIdx());
            out.setOutput(game.getPlayers()[action.getPlayerIdx()].getHand());
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_MANA)) {
            out.setPlayerIdx(action.getPlayerIdx());
            out.setOutput(game.getPlayers()[action.getPlayerIdx()].getMana());
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_TABLE)) {
            out.setOutput(game.getTable().getTable());
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_GAMES)) {
            out.setOutput(game.getGamesPlayed());
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_CARD)) {
            out.setOutput(game.getTable().getCard(action.getX(), action.getY()));
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_ENV)) {
            out.setPlayerIdx(action.getPlayerIdx());

            Player player = game.getPlayers()[action.getPlayerIdx()];
            ArrayList<CardInput> envCards = new ArrayList<CardInput>();

            for(CardInput card : player.getHand())
                if(Constants.ENV_CARDS.contains(card.getName()))
                    envCards.add(card);

            out.setOutput(envCards);
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_FROZEN)) {
            out.setOutput(game.getFrozenCards());
            System.out.println(game.getFrozenCards());
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_WINS_ONE)) {
            out.setOutput(game.getPlayers()[1].getWins());
            out.appendToArrayNode(game.getOutput());
        }

        if(command.equals(Constants.GET_WINS_TWO)) {
            out.setOutput(game.getPlayers()[2].getWins());
            out.appendToArrayNode(game.getOutput());
        }
    }
}
