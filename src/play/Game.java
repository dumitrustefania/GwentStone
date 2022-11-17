package play;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import util.Constants;
import fileio.GameInput;
import fileio.Input;
import play.players.Player;

import java.util.ArrayList;

/**
 *
 */
public class Game {
    protected Player[] players = new Player[Constants.NUM_PLAYERS + 1];
    protected int gamesPlayed = 0;
    protected ArrayNode output;

    /**
     * @param input
     * @throws JsonProcessingException
     */
    public void playGame(final Input input) throws JsonProcessingException {
        players[1] = new Player(1, 2, 3);
        players[1].setDecks(input.getPlayerOneDecks());

        players[2] = new Player(2, 1, 0);
        players[2].setDecks(input.getPlayerTwoDecks());

        ArrayList<GameInput> games = input.getGames();

        for (GameInput game : games) {
            players[1].initPlayer();
            players[2].initPlayer();

            gamesPlayed++;
            Match newMatch = new Match(game, players, gamesPlayed, output);
            newMatch.startGame();
        }
    }

    public Game(final ArrayNode output) {
        this.output = output;
    }

    public Game() {
    }

    public final int getGamesPlayed() {
        return gamesPlayed;
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
