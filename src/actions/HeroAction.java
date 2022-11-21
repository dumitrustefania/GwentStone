package actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.CardInput;
import abilities.hero_abilities.EmpressThorina;
import abilities.hero_abilities.GeneralKocioraw;
import abilities.hero_abilities.KingMudface;
import abilities.hero_abilities.LordRoyce;
import util.JsonOut;

import java.util.ArrayList;

/**
 * HeroAction class is used for performing specific actions
 * of the hero cards.
 */
public final class HeroAction extends Action {
    public HeroAction(final Action action) {
        super(action.action, action.match);
    }

    /**
     * Check the command name and execute the necessary steps accordingly.
     * @throws JsonProcessingException
     */
    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();

        if (command.equals(Constants.HERO_USE_ABILITY)) {
            this.useCardAbility();
        }
    }

    /**
     * Execute the hero's ability.
     * First check whether the command is a valid one.
     * Then perform the ability.
     * @throws JsonProcessingException
     */
    public void useCardAbility() throws JsonProcessingException {
        JsonOut out = new JsonOut();
        out.setCommand(action.getCommand());
        out.setAffectedRow(action.getAffectedRow());

        // get the player's hero card
        CardInput hero = match.getCurrentPlayer().getHero();

        if (hero.getMana() > match.getCurrentPlayer().getMana()) {
            out.setError("Not enough mana to use hero's ability.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        if (attackedThisTurn(hero)) {
            out.setError("Hero has already attacked this turn.");
            out.appendToArrayNode(match.getOutput());
            return;
        }

        // get the affected row
        ArrayList<CardInput> affectedRow = match.getTable().getTable().get(action.getAffectedRow());

        // create specific objects based on the hero's name
        // and perform its ability
        if (hero.getName().equals(Constants.LORD)) {
            if (!match.getOtherPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
                out.setError("Selected row does not belong to the enemy.");
                out.appendToArrayNode(match.getOutput());
                return;
            }

            new LordRoyce(affectedRow, match).useAbility();
        }


        if (hero.getName().equals(Constants.EMPRESS)) {
            if (!match.getOtherPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
                out.setError("Selected row does not belong to the enemy.");
                out.appendToArrayNode(match.getOutput());
                return;
            }

            new EmpressThorina(affectedRow, match).useAbility();
        }

        if (hero.getName().equals(Constants.KING)) {
            if (!match.getCurrentPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
                out.setError("Selected row does not belong to the current player.");
                out.appendToArrayNode(match.getOutput());
                return;
            }

            new KingMudface(affectedRow, match).useAbility();
        }

        if (hero.getName().equals(Constants.GENERAL)) {
            if (!match.getCurrentPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
                out.setError("Selected row does not belong to the current player.");
                out.appendToArrayNode(match.getOutput());
                return;
            }

            new GeneralKocioraw(affectedRow, match).useAbility();
        }

        // memorize that hero attacked
        match.getAttackedThisTurn().add(hero);

        // decrease player's mana
        match.getCurrentPlayer().setMana(match.getCurrentPlayer().getMana() - hero.getMana());
    }
}
