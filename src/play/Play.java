package play;
import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.GameInput;
import fileio.Input;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.players.Player;

import java.util.ArrayList;

public class Play {
    private Player[] players = new Player[3];

    public void play(Input input, ArrayNode output) throws JsonProcessingException {
        players[1] = new Player(1);
        players[1].setDecks(input.getPlayerOneDecks());

        players[2] = new Player(2);
        players[2].setDecks(input.getPlayerTwoDecks());

        ArrayList<GameInput> games = input.getGames();

        for(GameInput game : games) {
            Game newGame = new Game(game);
            newGame.startGame(this, output);
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
