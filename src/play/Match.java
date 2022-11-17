package play;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import util.Constants;
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.GameInput;
import play.actions.Action;
import play.players.Player;
import play.table.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The Match class contains all the data about the current match.
 */
public class Match extends Game {
    private final GameInput game;
    private final ArrayList<CardInput> tanks = new ArrayList<CardInput>();
    private final ArrayList<CardInput> frozenCards = new ArrayList<CardInput>();
    private final ArrayList<CardInput> attackedThisTurn = new ArrayList<CardInput>();
    private final Table table = new Table();
    private int roundsPlayed = 0;
    private int currActionIdx = 0;
    private Player currentPlayer;
    private Player otherPlayer;

    /**
     * Start the match by assigning the requested decks to the players and shuffling them
     * and assigning the picked hero.
     * Then, playing the rounds starts.
     * @throws JsonProcessingException
     */
    public void startMatch() throws JsonProcessingException {
        // initialize each player's fields for preparing the start
        players[1].initPlayer();
        players[2].initPlayer();

        // set decks
        players[1].setCurrentDeck(game.getStartGame().getPlayerOneDeckIdx());
        players[2].setCurrentDeck(game.getStartGame().getPlayerTwoDeckIdx());

        // shuffle decks
        int seed = game.getStartGame().getShuffleSeed();
        Collections.shuffle(players[1].getCurrentDeck(), new Random(seed));
        Collections.shuffle(players[2].getCurrentDeck(), new Random(seed));

        // set heroes
        players[1].setHero(game.getStartGame().getPlayerOneHero());
        players[2].setHero(game.getStartGame().getPlayerTwoHero());

        // start playing the game
        playRounds();
    }

    /**
     * Set up the player who starts at the beginning of each round.
     * Play round by round until reaching the end of the actions ArrayList.
     * @throws JsonProcessingException
     */
    public void playRounds() throws JsonProcessingException {
        // determine the starting player
        int firstPlayerNum = game.getStartGame().getStartingPlayer();
        int secondPlayerNum = 3 - firstPlayerNum;

        Player firstPlayer = players[firstPlayerNum];
        Player secondPlayer = players[secondPlayerNum];

        // play round by round
        do {
            roundsPlayed++;

            // add mana
            int manaAdded = Math.min(roundsPlayed, Constants.MAX_MANA_ADDED);
            firstPlayer.setMana(firstPlayer.getMana() + manaAdded);
            secondPlayer.setMana(secondPlayer.getMana() + manaAdded);

            //add card in hand
            firstPlayer.addCardInHandFromDeck();
            secondPlayer.addCardInHandFromDeck();

            //first player's turn
            currentPlayer = firstPlayer;
            otherPlayer = secondPlayer;
            playTurn(currentPlayer);

            //second player's turn
            this.currentPlayer = secondPlayer;
            this.otherPlayer = firstPlayer;
            playTurn(currentPlayer);

        } while (currActionIdx != game.getActions().size());
    }

    /**
     * Play a player's turn until reaching the "endPlayerTurn" command.
     * Create a new action object for each action in the list and perform the action.
     * @param player
     * @throws JsonProcessingException
     */
    public void playTurn(final Player player) throws JsonProcessingException {
        ArrayList<ActionsInput> actions = game.getActions();

        while (currActionIdx < actions.size()) {
            ActionsInput action = actions.get(currActionIdx);

            if (action.getCommand().equals(Constants.END_TURN)) {
                currActionIdx++;
                break;
            }

            Action newAction = new Action(action, this);
            newAction.performAction();
            currActionIdx++;
        }

        // when a player's turn ends, reinitialise its frozen card and its attackers
        unfreezeAndReinitCards(currentPlayer);
    }

    /**
     * @param player
     */
    public void unfreezeAndReinitCards(final Player player) {
        ArrayList<CardInput> row1 = table.getTable().get(player.getRowsAssigned().get(0));
        ArrayList<CardInput> row2 = table.getTable().get(player.getRowsAssigned().get(1));

        for (CardInput card : row1) {
            attackedThisTurn.remove(card);
            frozenCards.remove(card);
        }

        for (CardInput card : row2) {
            attackedThisTurn.remove(card);
            frozenCards.remove(card);
        }

        attackedThisTurn.remove(player.getHero());
    }

    public Match(final GameInput game, final Player[] players,
                 final int matchesPlayed, final ArrayNode output) {
        this.game = game;
        this.players = players;
        this.matchesPlayed = matchesPlayed;
        this.output = output;
    }

    public final ArrayList<CardInput> getTanks() {
        return tanks;
    }

    public final ArrayList<CardInput> getAttackedThisTurn() {
        return attackedThisTurn;
    }

    public final ArrayList<CardInput> getFrozenCards() {
        return frozenCards;
    }

    public final Table getTable() {
        return table;
    }

    public final Player getCurrentPlayer() {
        return currentPlayer;
    }

    public final Player getOtherPlayer() {
        return otherPlayer;
    }
}

