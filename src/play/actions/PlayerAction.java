package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.Constants;
import fileio.CardInput;
import play.players.Player;
import util.JSONout;

import java.util.ArrayList;

public class PlayerAction extends Action{
    public PlayerAction(Action action) {
        super(action.action, action.game);
    }

    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();
        JSONout out = new JSONout();
        out.setCommand(command);

        if(command.equals(Constants.PLACE_CARD)) {
            out.setHandIdx(action.getHandIdx());

            CardInput card = game.getCurrentPlayer().getHand().get(action.getHandIdx());
            Player player = game.getCurrentPlayer();

            // if card is env
            if(Constants.ENV_CARDS.contains(card.getName()))
                out.setError("Cannot place environment card on table.");

            //if not enough mana
            else if(card.getMana() > player.getMana())
                out.setError("Not enough mana to place card on table.");

            //if row is full
            else {
                int rowToBePlacedOn = Constants.ROW_TO_BE_PLACED_ON.get(card.getName());
                int playerNumber = player.getPlayerNum();
                int row;

                if(playerNumber == 1)
                    row = 2 +rowToBePlacedOn;
                else
                    row = 1 - rowToBePlacedOn;

                System.out.println(card +" "+ row);
                if(game.getTable().getTable().get(row).size() == 5)
                    out.setError("Cannot place card on table since row is full.");
                else {
                    if(card.getName().equals(Constants.WARDEN) || card.getName().equals(Constants.GOLIATH))
                        game.getTanks().add(card);

                    ArrayList<CardInput> rowOnTable = game.getTable().getTable().get(row);
                    rowOnTable.add(card);
                    player.getHand().remove(action.getHandIdx());
                    player.setMana(player.getMana() - card.getMana());
                    return;
                }
            }

            out.appendToArrayNode(game.getOutput());
        }
    }
}