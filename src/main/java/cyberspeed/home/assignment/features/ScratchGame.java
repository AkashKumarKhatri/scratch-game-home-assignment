package cyberspeed.home.assignment.features;

import cyberspeed.home.assignment.config.BlockArrangement;
import cyberspeed.home.assignment.config.Config;

import java.math.BigDecimal;
import java.util.Random;

public class ScratchGame {
    private final Config config;

    public ScratchGame(Config config) {
        this.config = config;
    }

    public PuzzlePattern scratch(BigDecimal bet) {
        return new PuzzlePatternScanner(config).analyze(generatePuzzle(), bet);
    }

    /**
     * Generates a puzzle pattern based on the provided configuration.
     * @return A PuzzlePattern object representing the generated puzzle.
     */
    private PuzzlePattern generatePuzzle() {
        // Check if bonus symbols are available in the configuration
        boolean isBonus = config.getProbabilities().getBonusSymbols() != null;

        // Create a 2D array to represent the game puzzle
        String[][] puzzle = new String[config.getRows()][config.getColumns()];

        // Initialize bonus symbol variable
        String bonusSymbol = null;

        // Iterate over the standard symbols' block arrangements
        for (BlockArrangement blockArrangement : config.getProbabilities().getStandardSymbols()) {
            // Check if bonus symbol is available and randomly decide whether to use it.
            if (isBonus && new Random().nextBoolean()) {
                // Generate a bonus symbol and assign it to the puzzle
                bonusSymbol = new SymbolAllotment(config.getProbabilities().getBonusSymbols()).next();
                puzzle[blockArrangement.getRow()][blockArrangement.getColumn()] = bonusSymbol;
                isBonus = false;    // Ensure bonus symbol is used only once
            } else {
                // Generate a standard symbol and assign it to the puzzle
                puzzle[blockArrangement.getRow()][blockArrangement.getColumn()] = new SymbolAllotment(blockArrangement).next();
            }
        }

        // Return the generated PuzzlePattern object
        return new PuzzlePattern(puzzle, BigDecimal.ZERO, null, bonusSymbol);
    }

}
