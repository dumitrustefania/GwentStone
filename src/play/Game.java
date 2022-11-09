package play;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.GameInput;
import play.players.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Game {
    private GameInput game;
    private int roundsPlayed = 0;

    public Game(GameInput game) {
        this.game = game;
    }

    public void startGame(Play play, ArrayNode output) {
        Player player1 = play.getPlayers()[1];
        Player player2 = play.getPlayers()[2];

        player1.setCurrentDeck(game.getStartGame().getPlayerOneDeckIdx());
        player2.setCurrentDeck(game.getStartGame().getPlayerOneDeckIdx());

        int seed = game.getStartGame().getShuffleSeed();
        Random rnd = new Random(seed);
        Collections.shuffle(player1.getCurrentDeck(), rnd);
        Collections.shuffle(player2.getCurrentDeck(), rnd);

        player1.setHero(game.getStartGame().getPlayerOneHero());
        player2.setHero(game.getStartGame().getPlayerTwoHero());

        playRounds(play);
    }

    public void playRounds(Play play) {
        int currentPlayer = game.getStartGame().getStartingPlayer();
        int nextPlayer = 3 - currentPlayer;

        Player firstPlayer = play.getPlayers()[currentPlayer];
        Player secondPlayer = play.getPlayers()[nextPlayer];

        ArrayList<ActionsInput> actions= game.getActions();
        int currActionIdx = 0;

        while(true) {
            roundsPlayed++;

            // add mana
            int manaAdded = Math.min(roundsPlayed, 10);
            firstPlayer.setMana(firstPlayer.getMana() + manaAdded);
            secondPlayer.setMana(secondPlayer.getMana() + manaAdded);

            //add card in hand
            firstPlayer.addCardInHandFromDeck();
            firstPlayer.addCardInHandFromDeck();
            break;

            //first player's turn
//            firstPlayer.doActions(actions, currActionIdx, game);
//            firstPlayer.unfreeze();

//            secondPlayer.doActions();
//            secondPlayer.unfreeze();

        }


    }
}
