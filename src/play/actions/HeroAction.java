package play.actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.Constants;
import fileio.CardInput;
import play.abilities.hero_abilities.EmpressThorina;
import play.abilities.hero_abilities.GeneralKocioraw;
import play.abilities.hero_abilities.KingMudface;
import play.abilities.hero_abilities.LordRoyce;
import util.JSONout;

import java.util.ArrayList;

public class HeroAction extends Action{
    public HeroAction(Action action) {
        super(action.action, action.game);
    }

    public void performAction() throws JsonProcessingException {
        String command = action.getCommand();

        if(command.equals(Constants.HERO_USE_ABILITY))
            this.UseCardAbility();
    }

    public void UseCardAbility() throws JsonProcessingException {
        JSONout out = new JSONout();
        out.setCommand(action.getCommand());
        out.setAffectedRow(action.getAffectedRow());

        CardInput hero = game.getCurrentPlayer().getHero();

        if(hero.getMana() > game.getCurrentPlayer().getMana()) {
            out.setError("Not enough mana to use hero's ability.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        if(attackedThisTurn(hero)) {
            out.setError("Hero has already attacked this turn.");
            out.appendToArrayNode(game.getOutput());
            return;
        }

        ArrayList<CardInput> affectedRow = game.getTable().getTable().get(action.getAffectedRow());
        System.out.println(hero.getName());
        if(hero.getName().equals(Constants.LORD)) {
            if(!game.getOtherPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
                out.setError("Selected row does not belong to the enemy.");
                out.appendToArrayNode(game.getOutput());
                return;
            }

            new LordRoyce(affectedRow, game).useAbility();
        }


        if(hero.getName().equals(Constants.EMPRESS)) {
            if(!game.getOtherPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
                out.setError("Selected row does not belong to the enemy.");
                out.appendToArrayNode(game.getOutput());
                return;
            }

            new EmpressThorina(affectedRow, game).useAbility();
        }

        if(hero.getName().equals(Constants.KING)) {
            if(!game.getCurrentPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
                out.setError("Selected row does not belong to the current player.");
                out.appendToArrayNode(game.getOutput());
                return;
            }

            new KingMudface(affectedRow, game).useAbility();
        }

        if(hero.getName().equals(Constants.GENERAL)) {
            if(!game.getCurrentPlayer().getRowsAssigned().contains(action.getAffectedRow())) {
                out.setError("Selected row does not belong to the current player.");
                out.appendToArrayNode(game.getOutput());
                return;
            }

            new GeneralKocioraw(affectedRow, game).useAbility();
        }

        game.getAttackedThisTurn().add(hero);
        game.getCurrentPlayer().setMana(game.getCurrentPlayer().getMana() - hero.getMana());
    }
}
