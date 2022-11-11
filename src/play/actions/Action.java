package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import fileio.ActionsInput;
import fileio.CardInput;
import play.Game;
import common.Constants;
import play.players.Player;

import java.util.ArrayList;

public class Action {
    protected ActionsInput action;
    protected Game game;

    public Action(ActionsInput action, Game game) {
        this.action = action;
        this.game = game;
    }

    public Action(Game game) {
        this.game = game;
    }

    public Action() {
    }

    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();
        System.out.println("table"+game.getTable().getTable());
        System.out.println("hand"+game.getCurrentPlayer().getHand());
        if (Constants.DEBUG_ACTIONS.contains(command))
            new DebugAction(this).performAction();

        else if (Constants.MINION_ACTIONS.contains(command))
            new MinionAction(this).performAction();

        else if (Constants.PLAYER_ACTIONS.contains(command))
            new PlayerAction(this).performAction();

        else if (Constants.HERO_ACTIONS.contains(command))
            new HeroAction(this).performAction();

        else if (Constants.ENV_ACTIONS.contains(command))
            new EnvironmentAction(this).performAction();

        System.out.println(game.getFrozenCards());
    }

    public ArrayList<CardInput> getRow(int idx, Player player) {
        int rowIdx = player.getRowsAssigned().get(idx);
        return game.getTable().getTable().get(rowIdx);
    }

    public boolean attackedThisTurn(CardInput card) {
        return game.getAttackedThisTurn().contains(card);
    }
}
