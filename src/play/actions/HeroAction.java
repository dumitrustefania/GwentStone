package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import util.Constants;
import fileio.CardInput;
import play.abilities.hero_abilities.EmpressThorina;
import play.abilities.hero_abilities.GeneralKocioraw;
import play.abilities.hero_abilities.KingMudface;
import play.abilities.hero_abilities.LordRoyce;
import util.JSONout;

import java.util.ArrayList;

/**
 *
 */
public final class HeroAction extends Action {
    public HeroAction(final Action action) {
        super(action.action, action.match);
    }

    /**
     * @throws JsonProcessingException
     */
    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();

        if (command.equals(Constants.HERO_USE_ABILITY)) {
            this.useCardAbility();
        }
    }

    /**
     * @throws JsonProcessingException
     */
    public void useCardAbility() throws JsonProcessingException {
        JSONout out = new JSONout();
        out.setCommand(action.getCommand());
        out.setAffectedRow(action.getAffectedRow());

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

        ArrayList<CardInput> affectedRow = match.getTable().getTable().get(action.getAffectedRow());

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

        match.getAttackedThisTurn().add(hero);
        match.getCurrentPlayer().setMana(match.getCurrentPlayer().getMana() - hero.getMana());
    }
}
