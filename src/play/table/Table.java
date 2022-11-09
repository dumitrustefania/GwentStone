package play.table;
import fileio.CardInput;

import java.util.ArrayList;
public final class Table {
    private CardInput[][] table = new CardInput[4][5];

    public CardInput[][] getTable() {
        return table;
    }

    public void setTable(CardInput[][] table) {
        this.table = table;
    }

    public CardInput[] getRow(int idx) {
        return table[idx];
    }
    public CardInput getCard(int row, int col) {
        return table[row][col];
    }

    public void setCard(int row, int col, CardInput newCard) {
        this.table[row][col] = newCard;
    }

}
