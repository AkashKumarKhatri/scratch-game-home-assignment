package cyberspeed.home.assignment.features;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Represents a puzzle pattern with its matrix, reward, applied winning combinations, and applied bonus symbol.
 * The matrix is a 2D array of strings representing the puzzle layout.
 * The reward is a BigDecimal representing the reward associated with the puzzle.
 * The applied winning combinations is a map where the keys represent the winning combinations and the values represent the symbols involved in each combination.
 * The applied bonus symbol is a string representing the bonus symbol applied in the puzzle.
 *
 * @author akash.kumar
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PuzzlePattern {
    private String[][] matrix;
    private BigDecimal reward;
    @JsonProperty("applied_winning_combinations")
    private Map<String, List<String>> appliedWinningCombinations;
    @JsonProperty("applied_bonus_symbol")
    private String appliedBonusSymbol;

    public PuzzlePattern() {
    }

    /**
     * Constructs a PuzzlePattern object with the specified matrix, reward, applied winning combinations, and applied bonus symbol.
     *
     * @param matrix                     The 2D array of strings representing the puzzle layout.
     * @param reward                     The reward associated with the puzzle.
     * @param winningCombinations The map of applied winning combinations.
     * @param bonusSymbol         The applied bonus symbol.
     */
    public PuzzlePattern(String[][] matrix, BigDecimal reward,
                         Map<String, List<String>> winningCombinations,
                         String bonusSymbol) {
        this.matrix = matrix;
        this.reward = reward;
        this.appliedWinningCombinations = winningCombinations;
        this.appliedBonusSymbol = bonusSymbol;
    }

    public String[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public Map<String, List<String>> getAppliedWinningCombinations() {
        return appliedWinningCombinations;
    }

    public void setAppliedWinningCombinations(Map<String, List<String>> appliedWinningCombinations) {
        this.appliedWinningCombinations = appliedWinningCombinations;
    }

    public String getAppliedBonusSymbol() {
        return appliedBonusSymbol;
    }

    public void setAppliedBonusSymbol(String appliedBonusSymbol) {
        this.appliedBonusSymbol = appliedBonusSymbol;
    }
}
