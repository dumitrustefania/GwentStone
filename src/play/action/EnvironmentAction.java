package play.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.Constants;
import fileio.CardInput;
import play.action.environment_abilities.Firestorm;
import play.action.minion_abilities.Ripper;
import play.players.Player;
import util.JSONout;

import java.util.ArrayList;

public class EnvironmentAction extends Action{
    public EnvironmentAction(Action action) {
        super(action.action, action.game);
    }

    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();

        if(command.equals(Constants.ENVIRONMENT_USE_ABILITY))
            this.UseCardAbility();
    }

    public void UseCardAbility() throws JsonProcessingException {
        JSONout out = new JSONout();
        out.setCommand(action.getCommand());
        out.setHandIdx(action.getHandIdx());
        out.setAffectedRow(action.getAffectedRow());

        CardInput envCard = game.getCurrentPlayer().getHand().get(action.getHandIdx());


        if(!Constants.ENV_CARDS.contains(envCard.getName())) {
            out.setError("Chosen card is not of type environment.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        if(envCard.getMana() > game.getCurrentPlayer().getMana()) {
            out.setError("Not enough mana to use environment card.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        if(!game.getOtherPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
            out.setError("Chosen row does not belong to the enemy.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        ArrayList<CardInput> affectedRow = game.getTable().getTable().get(action.getAffectedRow());

        if(envCard.getName().equals(Constants.FIRESTORM))
            new Firestorm(affectedRow, game, envCard, out).useAbility();

        if(envCard.getName().equals(Constants.WINTERFELL))
            new Firestorm(affectedRow, game, envCard, out).useAbility();

        if(envCard.getName().equals(Constants.HEARTHOUND))
            new Firestorm(affectedRow, game, envCard, out).useAbility();
    }
}
