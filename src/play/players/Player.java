package play.players;
import fileio.ActionsInput;
import fileio.CardInput;
import fileio.DecksInput;
import fileio.GameInput;
import play.Game;
import play.action.Action;

import java.util.ArrayList;

public class Player {
    private DecksInput decks;
    private ArrayList<CardInput> currentDeck;
    private ArrayList<CardInput> hand = new ArrayList<CardInput>();
    private int mana;
    private ArrayList<Integer> rowsAssigned = new ArrayList<Integer>();
    private CardInput hero;

    public ArrayList<Integer> getRowsAssigned() {
        return rowsAssigned;
    }

    public void setRowsAssigned(ArrayList<Integer> rowsAssigned) {
        this.rowsAssigned = rowsAssigned;
    }

    private int wins = 0;

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    private int playerNum;

    public Player(int playerNum, int row1, int row2) {
        this.playerNum = playerNum;
        this.rowsAssigned.add(row1);
        this.rowsAssigned.add(row2);
    }

    public Player() {
    }

    public void addCardInHandFromDeck() {
        System.out.println("THE CUURENT DECK IS:" + currentDeck);
        CardInput firstCardInDeck = currentDeck.get(0);
        hand.add(firstCardInDeck);
        currentDeck.remove(0);
    }

    public void unfreezeAndReinitMinions(Game game) {
        ArrayList<CardInput> row1 = game.getTable().getTable().get(rowsAssigned.get(0));
        ArrayList<CardInput> row2 = game.getTable().getTable().get(rowsAssigned.get(1));

        for(CardInput card : row1) {
            game.getAttackedThisTurn().remove(card);
            game.getFrozenCards().remove(card);
        }

        for(CardInput card : row2) {
            game.getAttackedThisTurn().remove(card);
            game.getFrozenCards().remove(card);
        }
    }

    public ArrayList<CardInput> getHand() {
        return hand;
    }

    public void setHand(ArrayList<CardInput> hand) {
        this.hand = hand;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public CardInput getHero() {
        return hero;
    }

    public void setHero(CardInput hero) {
        this.hero = hero;
        this.hero.setHealth(30);
    }

    public ArrayList<CardInput> getCurrentDeck() {
        return currentDeck;
    }

    public void setCurrentDeck(ArrayList<CardInput> currentDeck) {
        this.currentDeck = currentDeck;
    }
    public void setCurrentDeck(int deckIdx) {
        ArrayList<CardInput> deck = decks.getDecks().get(deckIdx);

        // Deep copy
        ArrayList<CardInput> deckCopy = new ArrayList<CardInput>();
        for(CardInput card : deck) {
            deckCopy.add(card);
        }

        this.currentDeck = deckCopy;
    }

    public DecksInput getDecks() {
        return decks;
    }

    public void setDecks(DecksInput decks) {
        this.decks = decks;
    }
}
