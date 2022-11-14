package play.table;

import util.Constants;
import fileio.CardInput;

import java.util.ArrayList;

/**
 *
 */
public final class Table {
    private ArrayList<ArrayList<CardInput>> table;

    public Table() {
        table = new ArrayList<ArrayList<CardInput>>();
        for (int row = 0; row < Constants.MAX_ROWS; row++) {
            ArrayList<CardInput> rowOnTable = new ArrayList<CardInput>();
            table.add(rowOnTable);
        }
    }

    public ArrayList<ArrayList<CardInput>> getTable() {
        return table;
    }

    /**
     * @param row
     * @param col
     * @return
     */
    public CardInput getCard(final int row, final int col) {
        return table.get(row).get(col);
    }

    /**
     * @param card
     */
    public void removeCard(final CardInput card) {
        for (ArrayList<CardInput> row : table) {
            if (row.contains(card)) {
                row.remove(card);
                break;
            }
        }
    }
}
