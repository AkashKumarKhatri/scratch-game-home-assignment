package cyberspeed.home.assignment.config;

import java.util.Map;

/**
 * @author akash.kumar
 */
public class BlockArrangement {
    private int column;
    private int row;
    private Map<String, Integer> symbols;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Map<String, Integer> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Integer> symbols) {
        this.symbols = symbols;
    }
}
