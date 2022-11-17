package play;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import util.Constants;
import fileio.GameInput;
import fileio.Input;
import play.players.Player;

import java.util.ArrayList;

public class Game {
    protected Player[] players = new Player[Constants.NUM_PLAYERS + 1];
    protected int matchesPlayed = 0;
    protected ArrayNode output;

    /**
     * Prepare the start of the game by creating the players and
     * assigning their decks.
     * Traverse the matches ArrayList and play each match.
     * @param input - the input parsed from the given JSON file
     * @throws JsonProcessingException
     */
    public void playGame(final Input input) throws JsonProcessingException {
        // create player 1
        players[1] = new Player(1, 2, 3);
        players[1].setDecks(input.getPlayerOneDecks());

        // create player 2
        players[2] = new Player(2, 1, 0);
        players[2].setDecks(input.getPlayerTwoDecks());

        ArrayList<GameInput> games = input.getGames();

        for (GameInput game : games) {
            matchesPlayed++;
            Match newMatch = new Match(game, players, matchesPlayed, output);
            newMatch.startMatch();
        }
    }

    public Game(final ArrayNode output) {
        this.output = output;
    }

    public Game() {
    }

    public final int getMatchesPlayed() {
        return matchesPlayed;
    }

    public final ArrayNode getOutput() {
        return output;
    }

    public final void setOutput(final ArrayNode output) {
        this.output = output;
    }

    public final Player[] getPlayers() {
        return players;
    }
}
