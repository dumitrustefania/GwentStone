package play;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.GameInput;
import play.action.Action;
import play.players.Player;
import common.Constants;
import play.table.Table;
import util.RandomInstance;

import javax.xml.stream.events.EndDocument;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Game extends Play{
    private final GameInput game;
    private Table table = new Table();

    private int roundsPlayed = 0;
    private int currActionIdx = 0;

    private Player currentPlayer;
    private Player otherPlayer;

    public Game(GameInput game, Player[] players, int gamesPlayed, ArrayNode output) {
        this.game = game;
        this.players = players;
        this.gamesPlayed = gamesPlayed;
        this.output = output;
    }

    public void startGame() throws JsonProcessingException {
        players[1].setCurrentDeck(game.getStartGame().getPlayerOneDeckIdx());
        players[2].setCurrentDeck(game.getStartGame().getPlayerTwoDeckIdx());
//        System.out.println("DECKKKK"+ players[2].getCurrentDeck());
        int seed = game.getStartGame().getShuffleSeed();
//        Random rnd = RandomInstance.get().getRnd();
//        rnd.setSeed(seed);

        Collections.shuffle(players[1].getCurrentDeck(), new Random(seed));
        Collections.shuffle(players[2].getCurrentDeck(), new Random(seed));

        players[1].setHero(game.getStartGame().getPlayerOneHero());
        players[2].setHero(game.getStartGame().getPlayerTwoHero());

        playRounds();
    }

    public void playRounds() throws JsonProcessingException {
        int player1 = game.getStartGame().getStartingPlayer();
        int player2 = 3 - player1;

        Player firstPlayer = players[player1];
        Player secondPlayer = players[player2];

        ArrayList<ActionsInput> actions= game.getActions();

        while(true) {
            roundsPlayed++;

            // add mana
            int manaAdded = Math.min(roundsPlayed, 10);
            firstPlayer.setMana(firstPlayer.getMana() + manaAdded);
            secondPlayer.setMana(secondPlayer.getMana() + manaAdded);

            //add card in hand
            firstPlayer.addCardInHandFromDeck();
            secondPlayer.addCardInHandFromDeck();

            //first player's turn
            this.currentPlayer = firstPlayer;
            this.otherPlayer = secondPlayer;
            while(currActionIdx < actions.size()) {
                ActionsInput action = actions.get(currActionIdx);
                System.out.println(action);
                if(action.getCommand().equals(Constants.END_TURN)) {
                    currActionIdx++;
                    break;
                }
                Action newAction = new Action(action, this);
                newAction.performAction();
                currActionIdx++;
                System.out.println(actions.size() + " " + currActionIdx);
        }

            this.currentPlayer = secondPlayer;
            this.otherPlayer = firstPlayer;
            while(currActionIdx < actions.size()) {
                ActionsInput action = actions.get(currActionIdx);
                System.out.println(action);

                if(action.getCommand().equals(Constants.END_TURN)) {
                    currActionIdx++;
                    break;
                }
                Action newAction = new Action(action, this);
                newAction.performAction();
                currActionIdx++;
                System.out.println(actions.size() + " " + currActionIdx);

            }
//            firstPlayer.unfreeze();
            if(currActionIdx == actions.size()) break;
//            secondPlayer.doActions();
//            secondPlayer.unfreeze();

        }


    }

    public Table getTable() {
        return table;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setTable(Table table) {
        this.table = table;
    }
    public Player getOtherPlayer() {
        return otherPlayer;
    }

    public void setOtherPlayer(Player otherPlayer) {
        this.otherPlayer = otherPlayer;
    }
}
