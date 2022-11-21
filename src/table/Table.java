package table;

import util.Constants;
import fileio.CardInput;

import java.util.ArrayList;

public final class Table {
    // the table has a matrix structure
    private final ArrayList<ArrayList<CardInput>> table;

    /**
     * Table constructor that allocates memory for the table
     * and for each row of the table.
     */
    public Table() {
        table = new ArrayList<ArrayList<CardInput>>();
        for (int row = 0; row < Constants.MAX_ROWS; row++) {
            table.add(new ArrayList<CardInput>());
        }
    }

    public ArrayList<ArrayList<CardInput>> getTable() {
        return table;
    }

    /**
     * Return the card on given row and column.
     * @param row
     * @param col
     * @return
     */
    public CardInput getCard(final int row, final int col) {
        if (table.get(row).size() < col) {
            return null;
        }

        return table.get(row).get(col);
    }

    /**
     * Remove the card from a given row and column.
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
