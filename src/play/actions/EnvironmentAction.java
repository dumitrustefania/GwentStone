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
 *
 */
public final class EnvironmentAction extends Action {
    public EnvironmentAction(final Action action) {
        super(action.action, action.game);
    }

    /**
     * @throws JsonProcessingException
     */
    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();

        if (command.equals(Constants.ENVIRONMENT_USE_ABILITY)) {
            this.useCardAbility();
        }
    }

    /**
     * @throws JsonProcessingException
     */
    public void useCardAbility() throws JsonProcessingException {
        JSONout out = new JSONout();
        out.setCommand(action.getCommand());
        out.setHandIdx(action.getHandIdx());
        out.setAffectedRow(action.getAffectedRow());

        CardInput envCard = game.getCurrentPlayer().getHand().get(action.getHandIdx());

        if (!Constants.ENV_CARDS.contains(envCard.getName())) {
            out.setError("Chosen card is not of type environment.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        if (envCard.getMana() > game.getCurrentPlayer().getMana()) {
            out.setError("Not enough mana to use environment card.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        if (!game.getOtherPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
            out.setError("Chosen row does not belong to the enemy.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        ArrayList<CardInput> affectedRow = game.getTable().getTable().get(action.getAffectedRow());

        if (envCard.getName().equals(Constants.FIRESTORM)) {
            new Firestorm(affectedRow, game, envCard, out).useAbility();
        }

        if (envCard.getName().equals(Constants.WINTERFELL)) {
            new Winterfell(affectedRow, game, envCard, out).useAbility();
        }

        if (envCard.getName().equals(Constants.HEARTHOUND)) {
            new HeartHound(affectedRow, game, envCard, out).useAbility();
        }
    }
}
