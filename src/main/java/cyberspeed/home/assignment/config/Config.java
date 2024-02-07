package cyberspeed.home.assignment.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * @author akash.kumar
 */
public class Config {
    private int columns;
    private int rows;
    private Map<String, Symbol> symbols;
    private Probabilities probabilities;
    @JsonProperty("win_combinations")
    private Map<String, WinDesign> winCombinations;

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Symbol> symbols) {
        this.symbols = symbols;
    }

    public Probabilities getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(Probabilities probabilities) {
        this.probabilities = probabilities;
    }

    public Map<String, WinDesign> getWinCombinations() {
        return winCombinations;
    }

    public void setWinCombinations(Map<String, WinDesign> winCombinations) {
        this.winCombinations = winCombinations;
    }

    /**
     * Basic validation of config.
     * @return true if config is valid
     */
    boolean isValid() {
        return columns > 0 && rows > 0
                && !symbols.isEmpty()
                && probabilities != null
                && !probabilities.standardSymbols.isEmpty()
                && probabilities.standardSymbols.size() == columns * rows   //and we hope they cover all cells
                && !winCombinations.isEmpty();
    }

    public static class Probabilities {
        @JsonProperty("standard_symbols")
        private List<BlockArrangement> standardSymbols;
        @JsonProperty("bonus_symbols")
        private BlockArrangement bonusSymbols;

        public List<BlockArrangement> getStandardSymbols() {
            return standardSymbols;
        }

        public void setStandardSymbols(List<BlockArrangement> standardSymbols) {
            this.standardSymbols = standardSymbols;
        }

        public BlockArrangement getBonusSymbols() {
            return bonusSymbols;
        }

        public void setBonusSymbols(BlockArrangement bonusSymbols) {
            this.bonusSymbols = bonusSymbols;
        }
    }
}
