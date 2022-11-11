package play.table;
import fileio.CardInput;

import java.util.ArrayList;
public final class Table {
    private ArrayList<ArrayList<CardInput>> table;

    public Table() {
        table = new ArrayList<ArrayList<CardInput>>();
        for(int row = 0; row < 4; row++) {
            ArrayList<CardInput> rowOnTable = new ArrayList<CardInput>();
            table.add(rowOnTable);
        }
    }

    public ArrayList<ArrayList<CardInput>> getTable() {
        return table;
    }

    public void setTable(ArrayList<ArrayList<CardInput>> table) {
        this.table = table;
    }

    public CardInput getCard(int row, int col) {
        return table.get(row).get(col);
    }

    public void removeCard(CardInput card) {
        for(ArrayList<CardInput> row : table) {
            if(row.contains(card)) {
                row.remove(card);
                break;
            }
        }
    }

}
