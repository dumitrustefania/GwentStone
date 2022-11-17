package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.CardInput;
import play.players.Player;
import util.JSONout;

import java.util.ArrayList;

/**
 *
 */
public final class PlayerAction extends Action {
    public PlayerAction(final Action action) {
        super(action.action, action.match);
    }

    /**
     * @throws JsonProcessingException
     */
    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();
        JSONout out = new JSONout();
        out.setCommand(command);

        if (command.equals(Constants.PLACE_CARD)) {
            out.setHandIdx(action.getHandIdx());

            CardInput card = match.getCurrentPlayer().getHand().get(action.getHandIdx());
            Player player = match.getCurrentPlayer();

            // if card is env
            if (Constants.ENV_CARDS.contains(card.getName())) {
                out.setError("Cannot place environment card on table.");
            } else if (card.getMana() > player.getMana()) {
                //if not enough mana
                out.setError("Not enough mana to place card on table.");
            } else { //if row is full
                int rowToBePlacedOn = Constants.ROW_TO_BE_PLACED_ON.get(card.getName());
                int playerNumber = player.getPlayerNum();
                int row;

                if (playerNumber == 1) {
                    row = 2 + rowToBePlacedOn;
                } else {
                    row = 1 - rowToBePlacedOn;
                }

                if (match.getTable().getTable().get(row).size() == Constants.MAX_COLS) {
                    out.setError("Cannot place card on table since row is full.");
                } else {
                    if (card.getName().equals(Constants.WARDEN)
                            || card.getName().equals(Constants.GOLIATH)) {
                        match.getTanks().add(card);
                    }

                    ArrayList<CardInput> rowOnTable = match.getTable().getTable().get(row);
                    rowOnTable.add(card);
                    player.getHand().remove(action.getHandIdx());
                    player.setMana(player.getMana() - card.getMana());
                    return;
                }
            }

            out.appendToArrayNode(match.getOutput());
        }
    }
}
