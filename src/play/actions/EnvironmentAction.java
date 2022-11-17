package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.CardInput;
import play.abilities.environment_abilities.Firestorm;
import play.abilities.environment_abilities.HeartHound;
import play.abilities.environment_abilities.Winterfell;
import util.JSONout;

import java.util.ArrayList;

/**
 * DebugAction class is used for performing specific actions
 * of the environment cards.
 */
public final class EnvironmentAction extends Action {
    public EnvironmentAction(final Action action) {
        super(action.action, action.match);
    }

    /**
     * Check the command name and execute the necessary steps accordingly.
     * @throws JsonProcessingException
     */
    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();

        if (command.equals(Constants.ENVIRONMENT_USE_ABILITY)) {
            this.useCardAbility();
        }
    }

    /**
     * Execute the environment card's ability.
     * First check whether the command is a valid one.
     * Then perform the ability.
     * @throws JsonProcessingException
     */
    public void useCardAbility() throws JsonProcessingException {
        JSONout out = new JSONout();
        out.setCommand(action.getCommand());
        out.setHandIdx(action.getHandIdx());
        out.setAffectedRow(action.getAffectedRow());

        // get the attacker card
        CardInput envCard = match.getCurrentPlayer().getHand().get(action.getHandIdx());

        if (!Constants.ENV_CARDS.contains(envCard.getName())) {
            out.setError("Chosen card is not of type environment.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        if (envCard.getMana() > match.getCurrentPlayer().getMana()) {
            out.setError("Not enough mana to use environment card.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        if (!match.getOtherPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
            out.setError("Chosen row does not belong to the enemy.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        // get the affected row
        ArrayList<CardInput> affectedRow = match.getTable().getTable().get(action.getAffectedRow());

        // create specific objects based on the card's type
        // and perform its ability
        if (envCard.getName().equals(Constants.FIRESTORM)) {
            new Firestorm(affectedRow, match, envCard, out).useAbility();
        }

        if (envCard.getName().equals(Constants.WINTERFELL)) {
            new Winterfell(affectedRow, match, envCard, out).useAbility();
        }

        if (envCard.getName().equals(Constants.HEARTHOUND)) {
            new HeartHound(affectedRow, match, envCard, out).useAbility();
        }
    }
}
