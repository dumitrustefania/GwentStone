package play;
import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.GameInput;
import fileio.Input;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.players.Player;

import java.util.ArrayList;

public class Play {
    protected Player[] players = new Player[3];
    protected int gamesPlayed = 0;
    protected ArrayNode output;

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public ArrayNode getOutput() {
        return output;
    }

    public void setOutput(ArrayNode output) {
        this.output = output;
    }

    public Play(ArrayNode output) {
        this.output = output;
    }

    public Play() {
    }

    public void play(Input input) throws JsonProcessingException {
        players[1] = new Player(1, 2 ,3);
        players[1].setDecks(input.getPlayerOneDecks());

        players[2] = new Player(2, 1, 0);
        players[2].setDecks(input.getPlayerTwoDecks());

        ArrayList<GameInput> games = input.getGames();

        for(GameInput game : games) {
            System.out.println("NEW GAMEEEEEEEEEEE");
            players[1].initPlayer();
            players[2].initPlayer();

            gamesPlayed++;
            Game newGame = new Game(game, players, gamesPlayed, output);
            newGame.startGame();
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
