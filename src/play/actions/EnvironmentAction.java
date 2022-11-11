package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.Constants;
import fileio.CardInput;
import play.abilities.environment_abilities.Firestorm;
import play.abilities.environment_abilities.HeartHound;
import play.abilities.environment_abilities.Winterfell;
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
        System.out.println("ENVCARDNAMEIS"+envCard.getName());


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

        System.out.println("affected row =" + action.getAffectedRow());
        System.out.println(game.getOtherPlayer().getPlayerNum());
        if(!game.getOtherPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
            out.setError("Chosen row does not belong to the enemy.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        ArrayList<CardInput> affectedRow = game.getTable().getTable().get(action.getAffectedRow());
        for(CardInput card:affectedRow)
            System.out.println(card);
        System.out.println("ENVCARDNAMEIS"+envCard.getName());
        if(envCard.getName().equals(Constants.FIRESTORM))
            new Firestorm(affectedRow, game, envCard, out).useAbility();

        if(envCard.getName().equals(Constants.WINTERFELL))
            new Winterfell(affectedRow, game, envCard, out).useAbility();

        if(envCard.getName().equals(Constants.HEARTHOUND))
            new HeartHound(affectedRow, game, envCard, out).useAbility();

        System.out.println(affectedRow);
    }
}
