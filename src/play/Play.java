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
        players[1] = new Player(1, 1 ,0);
        players[1].setDecks(input.getPlayerOneDecks());

        players[2] = new Player(2, 2, 3);
        players[2].setDecks(input.getPlayerTwoDecks());

        ArrayList<GameInput> games = input.getGames();

        for(GameInput game : games) {
            Game newGame = new Game(game, players, gamesPlayed, output);
            gamesPlayed++;
            newGame.startGame();
        }



//        System.out.println(input);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String smallOutput = objectMapper.writeValueAsString(inputData);
//        output.add(smallOutput);
//
//        System.out.println(output);
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
