package cyberspeed.home.assignment.features.scanners;

import cyberspeed.home.assignment.config.DesignMatch;
import cyberspeed.home.assignment.config.WinDesign;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A subclass of CombinationScanner that implements linear scanning for matching patterns in a puzzle.
 *
 * @author akash.kumar
 */

public class LinearScanner extends CombinationScanner {

    // List of PatternProfile objects representing combinations to be scanned for in the puzzle
    private final List<PatternProfile> combinations;

    /**
     * Constructs a LinearScanner object with the specified win combinations.
     *
     * @param winCombinations The map of win combinations defining patterns to be scanned for.
     */
    public LinearScanner(Map<String, WinDesign> winCombinations) {
        combinations = winCombinations.entrySet().stream()
                .filter(e -> e.getValue().getWhen() == DesignMatch.LINEAR_SYMBOLS)
                .map(e -> new PatternProfile(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Matches the given puzzle against the defined win combinations.
     *
     * @param puzzle The puzzle grid to match against.
     * @return A list of MatchingResult objects representing the matching results.
     */
    @Override
    public List<GameResult> match(String[][] puzzle) {
        // Stream over the combinations list and flatMap to stream over the covered areas of each combination
        return combinations.stream()
                .flatMap(cd -> cd.getWinCombination().getCoveredAreas().stream()
                        // Filter the covered areas based on whether they match the puzzle
                        .filter(coveredArea -> matchCoveredArea(puzzle, coveredArea))
                        // Map each matching covered area to a MatchingResult object
                        .map(coveredArea -> new GameResult(
                                puzzle[coveredArea.get(0).getRow()][coveredArea.get(0).getColumn()], // Get the symbol from the puzzle grid at the first coordinate of the covered area
                                cd.getName(), // Get the name of the combination descriptor
                                cd.getWinCombination().getGroup(), // Get the group of the win combination
                                cd.getWinCombination().getRewardMultiplier() // Get the reward multiplier of the win combination
                        )))
                // Collect the MatchingResult objects into a list
                .collect(Collectors.toList());
    }

    /**
     * Checks if the given puzzle matches the covered area defined by the win combination.
     *
     * @param puzzle      The puzzle grid to match against.
     * @param coveredArea The list of coordinates defining the covered area of the win combination.
     * @return true if the puzzle matches the covered area, false otherwise.
     */
    private boolean matchCoveredArea(String[][] puzzle, List<WinDesign.Coordinate> coveredArea) {
        // Initialize an iterator over the list of coordinates defining the covered area
        Iterator<WinDesign.Coordinate> iterator = coveredArea.iterator();

        // Retrieve the first coordinate from the covered area
        WinDesign.Coordinate first = iterator.next();

        // Extract the symbol from the puzzle grid at the coordinates defined by the first coordinate
        String symbol = puzzle[first.getRow()][first.getColumn()];

        // Iterate through the remaining coordinates in the covered area
        while (iterator.hasNext()) {
            // Retrieve the next coordinate from the covered area
            WinDesign.Coordinate next = iterator.next();

            // Compare the symbol extracted earlier with the symbol at the current coordinate in the puzzle grid
            if (!symbol.equals(puzzle[next.getRow()][next.getColumn()])) {
                // If symbols do not match, return false indicating the puzzle grid does not match the covered area
                return false;
            }
        }

        // If all symbols in the covered area match the corresponding symbols in the puzzle grid, return true
        return true;
    }
}
