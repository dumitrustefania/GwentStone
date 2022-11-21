package actions;

import util.Constants;
import fileio.CardInput;
import players.Player;
import util.JsonOut;

import java.util.ArrayList;

/**
 * PlayerAction class is used for performing specific
 * player actions.
 */
public final class PlayerAction extends Action {
    public PlayerAction(final Action action) {
        super(action.action, action.match);
    }

    /**
     * Check the command name and execute the necessary steps accordingly.
     */
    public void performAction() {
        String command = action.getCommand();
        JsonOut out = new JsonOut();
        out.setCommand(command);

        if (command.equals(Constants.PLACE_CARD)) {
            out.setHandIdx(action.getHandIdx());

            // get card from hand
            CardInput card = match.getCurrentPlayer().getHand().get(action.getHandIdx());
            Player player = match.getCurrentPlayer();

            if (Constants.ENV_CARDS.contains(card.getName())) {
                out.setError("Cannot place environment card on table.");
            } else if (card.getMana() > player.getMana()) {
                out.setError("Not enough mana to place card on table.");
            } else {
                // check whether card has to be placed on back or front row
                // rowToBePlacedOn = 0 -> front
                // rowToBePlacedOn = 0 -> back
                int rowToBePlacedOn = Constants.ROW_TO_BE_PLACED_ON.get(card.getName());
                int playerNumber = player.getPlayerNum();
                int row;

                // get the number of the row
                if (playerNumber == 1) {
                    row = 2 + rowToBePlacedOn;
                } else {
                    row = 1 - rowToBePlacedOn;
                }

                if (match.getTable().getTable().get(row).size() == Constants.MAX_COLS) {
                    out.setError("Cannot place card on table since row is full.");
                } else {
                    // add Warden/Goliath to tanks ArrayList
                    if (card.getName().equals(Constants.WARDEN)
                            || card.getName().equals(Constants.GOLIATH)) {
                        match.getTanks().add(card);
                    }

                    // place card on table
                    ArrayList<CardInput> rowOnTable = match.getTable().getTable().get(row);
                    rowOnTable.add(card);

                    // remove card from hand
                    player.getHand().remove(action.getHandIdx());

                    // decrease player's mana
                    player.setMana(player.getMana() - card.getMana());
                    return;
                }
            }

            out.appendToArrayNode(match.getOutput());
        }
    }
}
